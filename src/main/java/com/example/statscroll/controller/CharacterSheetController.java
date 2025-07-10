package com.example.statscroll.controller;

import com.example.statscroll.dao.CharactersDAO;
import com.example.statscroll.model.Characters;
import com.example.statscroll.util.CharacterPDFExporter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

public class CharacterSheetController {

    // Pagina 1 fields
    @FXML public TextField nameField;
    @FXML public ChoiceBox<String> classChoiceBox;
    @FXML public ChoiceBox<String> raceChoiceBox;
    @FXML public Spinner<Integer> levelSpinner;
    @FXML public TextField ageField;
    @FXML public TextField heightField;
    @FXML public TextField weightField;
    @FXML public TextField eyesField;
    @FXML public TextField hairField;
    @FXML public TextField skinField;

    // Pagina 2 fields
    @FXML public Spinner<Integer> strSpinner;
    @FXML public Spinner<Integer> dexSpinner;
    @FXML public Spinner<Integer> conSpinner;
    @FXML public Spinner<Integer> intSpinner;
    @FXML public Spinner<Integer> wisSpinner;
    @FXML public Spinner<Integer> chaSpinner;
    @FXML public Spinner<Integer> profBonSpinner;
    @FXML public Spinner<Integer> initiativeSpinner;
    @FXML public Spinner<Integer> maxHPSpinner;
    @FXML public TextField maxHitDiceField;
    @FXML public Spinner<Integer> inspSpinner;
    @FXML public TextArea inventoryField;

    @FXML
    public void initialize() {
        // Inizializza i ChoiceBox con le opzioni
        initializeChoiceBoxes();

        // Configura i campi numerici
        initializeNumericFields();

        // Configura gli spinner
        initializeSpinners();
    }

    private void initializeChoiceBoxes() {
        classChoiceBox.setItems(FXCollections.observableArrayList(
                "Barbaro", "Bardo", "Chierico", "Druido", "Guerriero",
                "Ladro", "Mago", "Monaco", "Paladino", "Ranger", "Stregone", "Warlock"));

        raceChoiceBox.setItems(FXCollections.observableArrayList(
                "Umano", "Elfo", "Nano", "Halfling", "Mezzelfo",
                "Mezzorco", "Tiefling", "Dragonide", "Gnomo"));

        // Imposta valori di default
        classChoiceBox.setValue("Guerriero");
        raceChoiceBox.setValue("Umano");
    }

    private void initializeNumericFields() {
        // Configura i TextField numerici
        setupNumericField(ageField);
        setupNumericField(heightField);
        setupNumericField(weightField);

        // Configura il campo per i dadi vita con formato speciale (es. 3d8)
        maxHitDiceField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*d\\d*")) {
                return change;
            }
            return null;
        }));
    }

    private void initializeSpinners() {
        // Configura gli spinner con valori appropriati
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

        // Formattazione per mostrare sempre il valore come numero
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

    public void handleNextClick(ActionEvent actionEvent) throws IOException {
        saveToSession();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterModificationPage2.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handlePreviousClick(ActionEvent actionEvent) throws IOException {
        saveToSession();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterModificationPage1.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleSaveClick(ActionEvent actionEvent) throws IOException {
        Characters character = createCharacterFromFields();

        CharactersDAO characterDAO = new CharactersDAO();
        characterDAO.save(character);

        // Torna al menu principale dopo il salvataggio
        returnToMenu(actionEvent);
    }

    public void handlePDFExport(ActionEvent actionEvent) {
        saveToSession();
        CharacterPDFExporter exporter = new CharacterPDFExporter();
        exporter.exportToPDF(Session.getCharacters(), Session.getCharacters().getName() + ".pdf");
    }

    private Characters createCharacterFromFields() {
        return new Characters(
                Session.getCharacters() != null ? Session.getCharacters().getId() : 0,
                Session.getUserId(),
                nameField.getText(),
                classChoiceBox.getValue(),
                levelSpinner.getValue(),
                raceChoiceBox.getValue(),
                initiativeSpinner.getValue(),
                30, // speed
                0, // armorclass
                strSpinner.getValue(),
                dexSpinner.getValue(),
                conSpinner.getValue(),
                intSpinner.getValue(),
                wisSpinner.getValue(),
                chaSpinner.getValue(),
                inspSpinner.getValue() == 1,
                profBonSpinner.getValue(),
                maxHPSpinner.getValue(),
                maxHPSpinner.getValue(), // currentHP
                0, // temporaryHP
                maxHitDiceField.getText(),
                maxHitDiceField.getText(), // currentHitDice
                0, // spellSaveDC
                0, // spellAttackBonus
                ageField.getText(),
                eyesField.getText(),
                hairField.getText(),
                skinField.getText(),
                heightField.getText(),
                weightField.getText(),
                inventoryField.getText()
        );
    }

    private void saveToSession() {
        Characters character = createCharacterFromFields();
        Session.setCharacters(character);
    }

    private void returnToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}