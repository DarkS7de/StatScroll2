package com.example.statscroll.controller;

import com.example.statscroll.dao.CharactersDAO;
import com.example.statscroll.model.Characters;
import com.example.statscroll.util.CharacterPDFExporter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

public class CharacterModificationPage2Controller {

    // Campi della pagina 2
    @FXML private Spinner<Integer> strSpinner;
    @FXML private Spinner<Integer> dexSpinner;
    @FXML private Spinner<Integer> conSpinner;
    @FXML private Spinner<Integer> intSpinner;
    @FXML private Spinner<Integer> wisSpinner;
    @FXML private Spinner<Integer> chaSpinner;
    @FXML private Spinner<Integer> profBonSpinner;
    @FXML private Spinner<Integer> initiativeSpinner;
    @FXML private Spinner<Integer> maxHPSpinner;
    @FXML private TextField hitDiceField;
    @FXML private CheckBox inspirationCheckBox;
    @FXML private Spinner<Integer> speedSpinner;
    @FXML private TextArea inventoryField;
    @FXML private Button previousButton;
    @FXML private Button saveButton;
    @FXML private Button pdfButton;

    private Characters currentCharacter;

    @FXML
    public void initialize() {
        initializeSpinners();
        setupHitDiceField();
    }

    public void setCharacter(Characters character) {
        this.currentCharacter = character;
        loadCharacterData();
    }

    private void initializeSpinners() {
        initializeSpinner(strSpinner, 1, 30, 10);
        initializeSpinner(dexSpinner, 1, 30, 10);
        initializeSpinner(conSpinner, 1, 30, 10);
        initializeSpinner(intSpinner, 1, 30, 10);
        initializeSpinner(wisSpinner, 1, 30, 10);
        initializeSpinner(chaSpinner, 1, 30, 10);
        initializeSpinner(profBonSpinner, 2, 6, 2);
        initializeSpinner(initiativeSpinner, -5, 10, 0);
        initializeSpinner(maxHPSpinner, 1, 500, 10);
        initializeSpinner(speedSpinner, 0, 100, 30);
    }

    private void initializeSpinner(Spinner<Integer> spinner, int min, int max, int initial) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initial));
        spinner.getEditor().setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), initial,
                change -> change.getControlNewText().matches("-?\\d*") ? change : null));
    }

    private void setupHitDiceField() {
        hitDiceField.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*d\\d+") ? change : null));
    }

    private void loadCharacterData() {
        if (currentCharacter != null) {
            strSpinner.getValueFactory().setValue(currentCharacter.getStr());
            dexSpinner.getValueFactory().setValue(currentCharacter.getDex());
            conSpinner.getValueFactory().setValue(currentCharacter.getCon());
            intSpinner.getValueFactory().setValue(currentCharacter.getIntel());
            wisSpinner.getValueFactory().setValue(currentCharacter.getWis());
            chaSpinner.getValueFactory().setValue(currentCharacter.getCha());
            profBonSpinner.getValueFactory().setValue(currentCharacter.getProfbonus());
            initiativeSpinner.getValueFactory().setValue(currentCharacter.getInitiative());
            maxHPSpinner.getValueFactory().setValue(currentCharacter.getMaxhp());
            hitDiceField.setText(currentCharacter.getHitdice());
            inspirationCheckBox.setSelected(currentCharacter.isInspiration());
            speedSpinner.getValueFactory().setValue(currentCharacter.getSpeed());
            inventoryField.setText(currentCharacter.getInventory());
        }
    }

    private void savePage2Data() {
        currentCharacter.setStr(strSpinner.getValue());
        currentCharacter.setDex(dexSpinner.getValue());
        currentCharacter.setCon(conSpinner.getValue());
        currentCharacter.setIntel(intSpinner.getValue());
        currentCharacter.setWis(wisSpinner.getValue());
        currentCharacter.setCha(chaSpinner.getValue());
        currentCharacter.setProfbonus(profBonSpinner.getValue());
        currentCharacter.setInitiative(initiativeSpinner.getValue());
        currentCharacter.setMaxhp(maxHPSpinner.getValue());
        currentCharacter.setHitdice(hitDiceField.getText());
        currentCharacter.setInspiration(inspirationCheckBox.isSelected());
        currentCharacter.setSpeed(speedSpinner.getValue());
        currentCharacter.setInventory(inventoryField.getText());
    }

    @FXML
    private void handlePreviousClick() {
        savePage2Data();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterModificationPage1.fxml"));
            Parent root = loader.load();

            CharacterModificationPage1Controller controller = loader.getController();
            controller.setCharacter(currentCharacter);

            Stage stage = (Stage) previousButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showErrorAlert("Errore nel caricamento della pagina", e.getMessage());
        }
    }

    @FXML
    private void handleSaveClick() {
        savePage2Data();
        CharactersDAO dao = new CharactersDAO();
        dao.update(currentCharacter);

        showInfoAlert("Personaggio aggiornato", "Le modifiche sono state salvate con successo.");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/myCharacters.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {  // Cambiato da 'e' a 'ex' per evitare conflitto
            showErrorAlert("Errore", "Impossibile tornare alla pagina dei personaggi: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handlePDFExport() {
        savePage2Data();
        CharacterPDFExporter exporter = new CharacterPDFExporter();
        exporter.exportToPDF(currentCharacter, currentCharacter.getName() + ".pdf");
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