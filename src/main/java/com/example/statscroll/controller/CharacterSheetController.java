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
import javafx.stage.Stage;

import java.io.IOException;

public class CharacterCreationController {

    // Pagina 1 fields
    @FXML public TextField nameField;
    @FXML public TextField classField;
    @FXML public TextField raceField;
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
        // Inizializza gli spinner con valori di default
        strSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        dexSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        conSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        intSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        wisSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        chaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        profBonSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 6, 2));
        levelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1));
        initiativeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-10, 20, 0));
        maxHPSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 10));
        inspSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1, 0));
    }

    public void handleNextClick(ActionEvent actionEvent) throws IOException {
        // Salva i dati nella sessione prima di passare alla pagina successiva
        saveToSession();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterModificationPage2.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handlePreviousClick(ActionEvent actionEvent) throws IOException {
        // Salva i dati nella sessione prima di tornare alla pagina precedente
        saveToSession();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterModificationPage1.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleSaveClick(ActionEvent actionEvent) throws IOException {
        Characters character = new Characters(
                0, // ID verrà generato dal DAO
                Session.getUserId(),
                nameField.getText(),
                classField.getText(),
                levelSpinner.getValue(),
                raceField.getText(),
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
                spellSaveDCSpinner.getValue(),
                spellAttackBonusSpinner.getValue(),
                ageField.getText(),
                eyesField.getText(),
                hairField.getText(),
                skinField.getText(),
                heightField.getText(),
                weightField.getText(),
                inventoryField.getText()
        );

        CharactersDAO characterDAO = new CharactersDAO();
        characterDAO.save(character);

        // Torna al menu principale dopo il salvataggio
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handlePDFExport(ActionEvent actionEvent) {
        saveToSession();
        CharacterPDFExporter exporter = new CharacterPDFExporter();
        exporter.exportToPDF(Session.getCharacters(), Session.getCharacters().getName() + ".pdf");
    }

    private void saveToSession() {
        Characters character = new Characters(
                Session.getCharacters() != null ? Session.getCharacters().getId() : 0,
                Session.getUserId(),
                nameField.getText(),
                classField.getText(),
                levelSpinner.getValue(),
                raceField.getText(),
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
        Session.setCharacters(character);
    }
}