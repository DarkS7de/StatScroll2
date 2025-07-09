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

public class CharacterSheetController {

    public TextField multiclassField;
    public TextField nameField;
    public TextField classField;
    public TextField subclassField;
    public TextField raceField;
    public TextField alignmentField;
    public TextField backgroundField;
    public Spinner<Integer> strSpinner;
    public Spinner<Integer> conSpinner;
    public Spinner<Integer> dexSpinner;
    public Spinner<Integer> intSpinner;
    public Spinner<Integer> wisSpinner;
    public Spinner<Integer> chaSpinner;
    public Spinner<Integer> inspSpinner;
    public Spinner<Integer> profBonSpinner;
    public Spinner<Integer> spellSaveDCSpinner;
    public Spinner<Integer> spellAttackBonusSpinner;
    public Spinner<Integer> initiativeSpinner;
    public Spinner<Integer> maxHPSpinner;
    public TextField maxHitDiceField;
    public TextField ageField;
    public TextField eyesField;
    public TextField hairField;
    public TextField skinField;
    public TextField heightField;
    public TextField weightField;
    public Button saveButton;
    public Spinner<Integer> levelSpinner;
    public Button previousButton;
    public ChoiceBox<String> classChoiceBox;
    public ChoiceBox<String> raceChoiceBox;

    @FXML
    public void initialize() {
        Characters selectedCharacter = Session.getCharacters();

        // Inizializza le ChoiceBox con le opzioni
        classChoiceBox.setItems(FXCollections.observableArrayList(
                "Paladino", "Guerriero", "Ladro", "Chierico", "Druido", "Mago"));
        raceChoiceBox.setItems(FXCollections.observableArrayList(
                "Umano", "Elfo", "Mezzelfo", "Tiefling", "Nano"));

        nameField.setText(selectedCharacter.getName());
        classField.setText(selectedCharacter.getChar_class());
        raceField.setText(selectedCharacter.getRace());
        maxHitDiceField.setText(selectedCharacter.getHitdice());
        ageField.setText(selectedCharacter.getAge());
        eyesField.setText(selectedCharacter.getEyes());
        hairField.setText(selectedCharacter.getHair());
        skinField.setText(selectedCharacter.getSkin());
        heightField.setText(selectedCharacter.getHeight());
        weightField.setText(selectedCharacter.getWeight());

        strSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        dexSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        conSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        intSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        wisSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        chaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));

        // Bonus competenza (2–6 tipico range D&D 5e)
        profBonSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 6, 2));

        // Level (1–20)
        levelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1));

        // Initiative (può essere negativo in D&D)
        initiativeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-10, 20, 0));

        // HP (0–999)
        maxHPSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 10));

        // Spell Save DC (8–30)
        spellSaveDCSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 30, 10));

        // Spell Attack Bonus (0–20)
        spellAttackBonusSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 5));

        // Inspiration (0 o 1, booleano mascherato)
        inspSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1, 0));

        strSpinner.getValueFactory().setValue(selectedCharacter.getStr());
        dexSpinner.getValueFactory().setValue(selectedCharacter.getDex());
        conSpinner.getValueFactory().setValue(selectedCharacter.getCon());
        intSpinner.getValueFactory().setValue(selectedCharacter.getIntel());
        wisSpinner.getValueFactory().setValue(selectedCharacter.getWis());
        chaSpinner.getValueFactory().setValue(selectedCharacter.getCha());
        maxHPSpinner.getValueFactory().setValue(selectedCharacter.getMaxhp());
        spellSaveDCSpinner.getValueFactory().setValue(selectedCharacter.getSpellsavedc());
        spellAttackBonusSpinner.getValueFactory().setValue(selectedCharacter.getSpellattackbonus());
        initiativeSpinner.getValueFactory().setValue(selectedCharacter.getInitiative());
        levelSpinner.getValueFactory().setValue(selectedCharacter.getLevel());
        profBonSpinner.getValueFactory().setValue(selectedCharacter.getProfbonus());
        int n = selectedCharacter.isInspiration() ? 1 : 0;
        inspSpinner.getValueFactory().setValue(n);
    }

    CharactersDAO characterDAO = new CharactersDAO();
    public void handleSaveClick(ActionEvent actionEvent) throws IOException {
        Characters character = new Characters(
                Session.getCharacters().getId(),
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
                false,
                profBonSpinner.getValue(),
                maxHPSpinner.getValue(),
                maxHPSpinner.getValue(),
                0,
                maxHitDiceField.getText(),
                maxHitDiceField.getText(),
                spellSaveDCSpinner.getValue(),
                spellAttackBonusSpinner.getValue(),
                ageField.getText(),
                eyesField.getText(),
                hairField.getText(),
                skinField.getText(),
                heightField.getText(),
                weightField.getText(),
                "NONE"
        );
        Session.setCharacters(character);
        System.out.println(Session.getUserId());
        System.out.println(Session.getCharacters());
        characterDAO.update(character);
    }

    public void handlePreviousClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handlePDFExport(ActionEvent actionEvent) {
        CharacterPDFExporter exporter = new CharacterPDFExporter();
        exporter.exportToPDF(Session.getCharacters(), Session.getCharacters().getName() + ".pdf");
    }
}
