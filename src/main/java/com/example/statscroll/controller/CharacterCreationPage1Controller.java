package com.example.statscroll.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.statscroll.model.Characters;

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

        // Inizializza le ChoiceBox con i valori predefiniti
        classChoiceBox.getItems().addAll("Guerriero", "Mago", "Ladro", "Chierico", "Ranger", "Stregone");
        raceChoiceBox.getItems().addAll("Umano", "Elfo", "Nano", "Halfling", "Mezzelfo", "Tiefling");

        // Imposta i valori di default
        levelSpinner.getValueFactory().setValue(1);

        // Configura il pulsante Wiki
        wikiButton.setOnAction(event -> openWiki());
    }

    @FXML
    private void handleNextClick() {
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
            // Carica la seconda pagina
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/statscroll/view/characterCreationPage2.fxml"));
            CharacterCreationPage2Controller page2Controller = new CharacterCreationPage2Controller(character);
            loader.setController(page2Controller);
            Parent root = loader.load();

            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
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
        }
    }
}