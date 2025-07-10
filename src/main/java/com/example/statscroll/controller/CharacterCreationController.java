package com.example.statscroll.controller;

import com.example.statscroll.dao.CharactersDAO;
import com.example.statscroll.model.Characters;
import com.example.statscroll.util.CharacterPDFExporter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;

public class CharacterCreationController {

    // Liste predefinite
    private final String[] CLASSES = {"Paladino", "Guerriero", "Ladro", "Chierico", "Druido", "Mago"};
    private final String[] RACES = {"Umano", "Elfo", "Mezzelfo", "Tiefling", "Nano"};

    // Pagina 1 fields
    @FXML private TextField nameField;
    @FXML private ChoiceBox<String> classChoiceBox;
    @FXML private ChoiceBox<String> raceChoiceBox;
    @FXML private Spinner<Integer> levelSpinner;
    @FXML private TextField ageField;
    @FXML private TextField heightField;
    @FXML private TextField weightField;
    @FXML private TextField eyesField;
    @FXML private TextField hairField;
    @FXML private TextField skinField;

    // Pagina 2 fields
    @FXML private Spinner<Integer> strSpinner;
    @FXML private Spinner<Integer> dexSpinner;
    @FXML private Spinner<Integer> conSpinner;
    @FXML private Spinner<Integer> intSpinner;
    @FXML private Spinner<Integer> wisSpinner;
    @FXML private Spinner<Integer> chaSpinner;
    @FXML private Spinner<Integer> profBonSpinner;
    @FXML private Spinner<Integer> initiativeSpinner;
    @FXML private Spinner<Integer> maxHPSpinner;
    @FXML private TextField maxHitDiceField;
    @FXML private Spinner<Integer> inspSpinner;
    @FXML private TextArea inventoryField;

    @FXML
    public void initialize() {
        initializeChoiceBoxes();
        initializeNumericFields();
        initializeSpinners();
    }

    private void initializeChoiceBoxes() {
        classChoiceBox.setItems(FXCollections.observableArrayList(CLASSES));
        raceChoiceBox.setItems(FXCollections.observableArrayList(RACES));
        classChoiceBox.setValue("Guerriero");
        raceChoiceBox.setValue("Umano");
    }

    private void initializeNumericFields() {
        setupNumericField(ageField);
        setupNumericField(heightField);
        setupNumericField(weightField);
        maxHitDiceField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*d\\d*")) {
                return change;
            }
            return null;
        }));
    }

    private void initializeSpinners() {
        initializeSpinner(levelSpinner, 1, 20, 1);
        initializeSpinner(strSpinner, 1, 20, 10);
        initializeSpinner(dexSpinner, 1, 20, 10);
        initializeSpinner(conSpinner, 1, 20, 10);
        initializeSpinner(intSpinner, 1, 20, 10);
        initializeSpinner(wisSpinner, 1, 20, 10);
        initializeSpinner(chaSpinner, 1, 20, 10);
        initializeSpinner(profBonSpinner, 2, 6, 2);
        initializeSpinner(initiativeSpinner, -10, 20, 0);
        initializeSpinner(maxHPSpinner, 1, 999, 10);
        initializeSpinner(inspSpinner, 0, 1, 0);
    }

    private void initializeSpinner(Spinner<Integer> spinner, int min, int max, int initial) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initial));
        spinner.getEditor().setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), initial,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("-?\\d*")) {
                        return change;
                    }
                    return null;
                }));
    }

    private void setupNumericField(TextField field) {
        field.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));
    }

    @FXML
    private void handleNextClick(ActionEvent actionEvent) throws IOException {
        if (isPage1Visible()) {
            if (nameField.getText().trim().isEmpty()) {
                showError("Il nome del personaggio è obbligatorio");
                return;
            }
            navigateToPage2();
        } else {
            Characters character = createCharacterFromInput();
            if (validateCharacter(character)) {
                saveCharacter(character);
                navigateToMenuPage();
            }
        }
    }

    @FXML
    private void handlePreviousClick(ActionEvent actionEvent) throws IOException {
        if (isPage2Visible()) {
            navigateToPage1();
        } else {
            navigateToMenuPage();
        }
    }

    @FXML
    private void handlePDFExport(ActionEvent actionEvent) {
        Characters character = createCharacterFromInput();
        CharacterPDFExporter exporter = new CharacterPDFExporter();
        exporter.exportToPDF(character, character.getName() + ".pdf");
    }

    private Characters createCharacterFromInput() {
        return new Characters(
                0,
                Session.getUserId(),
                nameField.getText(),
                classChoiceBox.getValue(),
                levelSpinner.getValue(),
                raceChoiceBox.getValue(),
                initiativeSpinner.getValue(),
                30,
                0,
                strSpinner.getValue(),
                dexSpinner.getValue(),
                conSpinner.getValue(),
                intSpinner.getValue(),
                wisSpinner.getValue(),
                chaSpinner.getValue(),
                inspSpinner.getValue() == 1,
                profBonSpinner.getValue(),
                maxHPSpinner.getValue(),
                maxHPSpinner.getValue(),
                0,
                maxHitDiceField.getText(),
                maxHitDiceField.getText(),
                0,
                0,
                ageField.getText(),
                eyesField.getText(),
                hairField.getText(),
                skinField.getText(),
                heightField.getText(),
                weightField.getText(),
                inventoryField.getText()
        );
    }

    private void saveCharacter(Characters character) {
        CharactersDAO characterDAO = new CharactersDAO();
        characterDAO.save(character);
        Session.setCharacters(character);
    }

    private boolean validateCharacter(Characters character) {
        if (character.getName().trim().isEmpty()) {
            showError("Il nome è obbligatorio");
            return false;
        }
        return true;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isPage1Visible() {
        return nameField != null && nameField.getScene() != null;
    }

    private boolean isPage2Visible() {
        return inventoryField != null && inventoryField.getScene() != null;
    }

    private void navigateToPage1() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();

            // Percorso assoluto (sostituisci con il tuo percorso reale)
            String absolutePath = "file:/C:/percorso/al/tuo/progetto/src/main/resources/fxml/characterCreationPage1.fxml";

            loader.setLocation(new URL(absolutePath));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node)inventoryField).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            showError("Percorso del file non valido");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Impossibile caricare la pagina");
        }
    }

    private void navigateToPage2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterCreationPage2.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void navigateToMenuPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)inventoryField).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}