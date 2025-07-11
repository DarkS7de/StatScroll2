package com.example.statscroll.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.statscroll.model.Characters;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.function.UnaryOperator;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public class CharacterCreationPage1Controller {

    private Characters character;

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
    @FXML private Button nextButton;
    @FXML private Button wikiButton;

    @FXML
    public void initialize() {
        character = new Characters();

        // Configurazione dei filtri per input numerici
        configureNumericField(ageField);    // Solo numeri interi per l'età
        configureNumericField(heightField); // Solo numeri interi per l'altezza
        configureNumericField(weightField); // Solo numeri interi per il peso

        // Inizializza le ChoiceBox
        classChoiceBox.getItems().addAll("Guerriero", "Mago", "Ladro", "Chierico", "Ranger", "Stregone");
        raceChoiceBox.getItems().addAll("Umano", "Elfo", "Nano", "Halfling", "Mezzelfo", "Tiefling");

        // Configurazione dello Spinner
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        levelSpinner.setValueFactory(valueFactory);
        levelSpinner.setEditable(true);

        // Validazione input manuale nello Spinner
        levelSpinner.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            try {
                if (!newValue.isEmpty()) {
                    int value = Integer.parseInt(newValue);
                    if (value < 1 || value > 20) {
                        levelSpinner.getEditor().setText(oldValue);
                    }
                }
            } catch (NumberFormatException e) {
                levelSpinner.getEditor().setText(oldValue);
            }
        });

        wikiButton.setOnAction(event -> openWiki());
    }

    // Metodo per configurare i campi di testo numerici
    private void configureNumericField(TextField textField) {
        UnaryOperator<Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) { // Accetta solo numeri interi
                return change;
            }
            return null; // Blocca l'input non numerico
        };
        textField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, integerFilter));
    }

    @FXML
    private void handleNextClick() {
        // Validazione dei campi obbligatori
        if (nameField.getText().isEmpty() ||
                classChoiceBox.getValue() == null ||
                raceChoiceBox.getValue() == null) {

            showAlert("Campi obbligatori mancanti", "Compila tutti i campi obbligatori");
            return;
        }

        // Validazione campi numerici
        if (ageField.getText().isEmpty() || heightField.getText().isEmpty() || weightField.getText().isEmpty()) {
            showAlert("Campi mancanti", "Compila tutti i campi numerici (età, altezza, peso)");
            return;
        }

        // Salva i dati nella classe Characters
        character.setName(nameField.getText());
        character.setChar_class(classChoiceBox.getValue());
        character.setRace(raceChoiceBox.getValue());
        character.setLevel(levelSpinner.getValue());
        character.setAge(ageField.getText());
        character.setHeight(heightField.getText());
        character.setWeight(weightField.getText());
        character.setEyes(eyesField.getText());
        character.setHair(hairField.getText());
        character.setSkin(skinField.getText());

        try {
            URL fxmlUrl = getClass().getResource("/fxml/characterCreationPage2.fxml");
            if (fxmlUrl == null) {
                throw new IOException("File FXML non trovato");
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            CharacterCreationPage2Controller page2Controller = new CharacterCreationPage2Controller(character);
            loader.setController(page2Controller);  // Imposta il controller qui
            Parent root = loader.load();

            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Errore", "Impossibile caricare la pagina successiva: " + e.getMessage());
        }
    }

    private void openWiki() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/statscroll/view/wikiPage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Wiki Personaggi");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Errore", "Impossibile aprire la pagina Wiki");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}