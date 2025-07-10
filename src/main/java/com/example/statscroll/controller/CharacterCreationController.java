package com.example.statscroll.controller;

import com.example.statscroll.dao.CharactersDAO;
import com.example.statscroll.model.Characters;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CharacterCreationController {
    // Liste predefinite
    private final String[] CLASSES = {"Paladino", "Guerriero", "Ladro", "Chierico", "Druido", "Mago"};
    private final String[] RACES = {"Umano", "Elfo", "Mezzelfo", "Tiefling", "Nano"};

    // Campi per la pagina 1
    @FXML private TextField nameField;
    @FXML private TextField classField;
    @FXML private TextField subclassField;
    @FXML private TextField raceField;
    @FXML private Spinner<Integer> levelSpinner;
    @FXML private TextField alignmentField;
    @FXML private TextField backgroundField;
    @FXML private TextField ageField;
    @FXML private TextField heightField;
    @FXML private TextField weightField;
    @FXML private TextField eyesField;
    @FXML private TextField hairField;
    @FXML private TextField skinField;

    // Campi per la pagina 2
    @FXML private Spinner<Integer> strSpinner;
    @FXML private Spinner<Integer> dexSpinner;
    @FXML private Spinner<Integer> conSpinner;
    @FXML private Spinner<Integer> wisSpinner;
    @FXML private Spinner<Integer> chaSpinner;
    @FXML private Spinner<Integer> intSpinner;
    @FXML private Spinner<Integer> inspSpinner;
    @FXML private Spinner<Integer> profBonSpinner;
    @FXML private Spinner<Integer> spellSaveDCSpinner;
    @FXML private Spinner<Integer> spellAttackBonusSpinner;
    @FXML private Spinner<Integer> initiativeSpinner;
    @FXML private Spinner<Integer> maxHPSpinner;
    @FXML private TextField maxHitDiceField;
    @FXML private TextArea inventoryField;

    // Pulsanti
    @FXML private Button previousButton;
    @FXML private Button nextButton;

    private final CharactersDAO charactersDAO = new CharactersDAO();

    @FXML
    public void initialize() {
        setupSpinners();
    }

    private void setupSpinners() {
        // Spinner per le statistiche (pagina 2)
        setupStatSpinner(strSpinner, 10);
        setupStatSpinner(dexSpinner, 10);
        setupStatSpinner(conSpinner, 10);
        setupStatSpinner(intSpinner, 10);
        setupStatSpinner(wisSpinner, 10);
        setupStatSpinner(chaSpinner, 10);

        // Altri spinner
        profBonSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 6, 2));
        levelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1));
        initiativeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-10, 20, 0));
        maxHPSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 10));
        spellSaveDCSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 30, 10));
        spellAttackBonusSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 5));
        inspSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1, 0));
    }

    private void setupStatSpinner(Spinner<Integer> spinner, int defaultValue) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, defaultValue));
    }

    @FXML
    private void handleNextClick(ActionEvent actionEvent) throws IOException {
        if (isPage1Visible()) {
            // Validazione pagina 1
            if (nameField.getText().trim().isEmpty()) {
                showError("Il nome del personaggio è obbligatorio");
                return;
            }
            navigateToPage2();
        } else {
            // Validazione pagina 2 e salvataggio
            Characters character = createCharacterFromInput();
            if (validateCharacter(character)) {
                Session.setCharacters(character);
                charactersDAO.save(character);
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

    private boolean isPage1Visible() {
        return nameField != null && nameField.getScene() != null;
    }

    private boolean isPage2Visible() {
        return inventoryField != null && inventoryField.getScene() != null;
    }

    private Characters createCharacterFromInput() {
        return new Characters(
                0, // ID temporaneo
                String.valueOf(Session.getUserId()),
                nameField.getText(),
                classField.getText(),
                levelSpinner.getValue(),
                raceField.getText(),
                initiativeSpinner.getValue(),
                30, // speed
                0, // exp
                strSpinner.getValue(),
                dexSpinner.getValue(),
                conSpinner.getValue(),
                intSpinner.getValue(),
                wisSpinner.getValue(),
                chaSpinner.getValue(),
                inspSpinner.getValue() == 1, // inspiration
                profBonSpinner.getValue(),
                maxHPSpinner.getValue(),
                maxHPSpinner.getValue(), // currentHP = maxHP
                0, // tempHP
                maxHitDiceField.getText(),
                maxHitDiceField.getText(), // hitDice = totalHitDice
                spellSaveDCSpinner.getValue(),
                spellAttackBonusSpinner.getValue(),
                ageField.getText(),
                eyesField.getText(),
                hairField.getText(),
                skinField.getText(),
                heightField.getText(),
                weightField.getText(),
                inventoryField.getText() // inventory
        );
    }

    private boolean validateCharacter(Characters character) {
        if (character.getName() == null || character.getName().trim().isEmpty()) {
            showError("Il nome è obbligatorio");
            return false;
        }

        if (character.getChar_class() == null || character.getChar_class().trim().isEmpty()) {
            showError("La classe è obbligatoria");
            return false;
        }

        if (character.getRace() == null || character.getRace().trim().isEmpty()) {
            showError("La razza è obbligatoria");
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

    private void navigateToPage1() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterCreationPage1.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) previousButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void navigateToPage2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterCreationPage2.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) nextButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void navigateToMenuPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) nextButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}