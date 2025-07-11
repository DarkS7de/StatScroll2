package com.example.statscroll.controller;

import com.example.statscroll.dao.CharactersDAO;
import com.example.statscroll.model.Characters;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

public class CharacterModificationPage1Controller {

    // Campi della pagina 1
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
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    private Characters currentCharacter;

    @FXML
    public void initialize() {
        // Inizializza le ChoiceBox
        initializeChoiceBoxes();

        // Configura i campi numerici
        initializeNumericFields();

        // Configura gli spinner
        initializeSpinners();
    }

    public void setCharacter(Characters character) {
        this.currentCharacter = character;
        loadCharacterData();
    }

    private void initializeChoiceBoxes() {
        classChoiceBox.setItems(FXCollections.observableArrayList(
                "Barbaro", "Bardo", "Chierico", "Druido", "Guerriero",
                "Ladro", "Mago", "Monaco", "Paladino", "Ranger", "Stregone", "Warlock"));

        raceChoiceBox.setItems(FXCollections.observableArrayList(
                "Umano", "Elfo", "Nano", "Halfling", "Mezzelfo",
                "Mezzorco", "Tiefling", "Dragonide", "Gnomo"));
    }

    private void initializeNumericFields() {
        // Configura i TextField numerici
        setupNumericField(ageField);
        setupNumericField(heightField);
        setupNumericField(weightField);
    }

    private void initializeSpinners() {
        initializeSpinner(levelSpinner, 1, 20, 1);
    }

    private void initializeSpinner(Spinner<Integer> spinner, int min, int max, int initial) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initial));
        spinner.getEditor().setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), initial,
                change -> change.getControlNewText().matches("-?\\d*") ? change : null));
    }

    private void setupNumericField(TextField field) {
        field.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null));
    }

    private void loadCharacterData() {
        if (currentCharacter != null) {
            nameField.setText(currentCharacter.getName());
            classChoiceBox.setValue(currentCharacter.getChar_class());
            raceChoiceBox.setValue(currentCharacter.getRace());
            levelSpinner.getValueFactory().setValue(currentCharacter.getLevel());
            ageField.setText(currentCharacter.getAge());
            heightField.setText(currentCharacter.getHeight());
            weightField.setText(currentCharacter.getWeight());
            eyesField.setText(currentCharacter.getEyes());
            hairField.setText(currentCharacter.getHair());
            skinField.setText(currentCharacter.getSkin());
        }
    }

    private void savePage1Data() {
        currentCharacter.setName(nameField.getText());
        currentCharacter.setChar_class(classChoiceBox.getValue());
        currentCharacter.setRace(raceChoiceBox.getValue());
        currentCharacter.setLevel(levelSpinner.getValue());
        currentCharacter.setAge(ageField.getText());
        currentCharacter.setHeight(heightField.getText());
        currentCharacter.setWeight(weightField.getText());
        currentCharacter.setEyes(eyesField.getText());
        currentCharacter.setHair(hairField.getText());
        currentCharacter.setSkin(skinField.getText());
    }

    @FXML
    private void handleNextClick() {
        savePage1Data();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterModificationPage2.fxml"));
            loader.setControllerFactory(clazz -> new CharacterModificationPage2Controller());
            Parent root = loader.load();

            CharacterModificationPage2Controller controller = loader.getController();
            controller.setCharacter(currentCharacter);

            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showErrorAlert("Errore nel caricamento della pagina", e.getMessage());
        }
    }

    @FXML
    private void handleSaveClick() {
        savePage1Data();
        // Salva il personaggio nel database
        CharactersDAO dao = new CharactersDAO();
        dao.update(currentCharacter);

        showInfoAlert("Personaggio aggiornato", "Le modifiche sono state salvate con successo.");
    }

    @FXML
    private void handleCancelClick() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/menuPage.fxml"));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showErrorAlert("Errore nel tornare al menu", e.getMessage());
        }
    }


    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}