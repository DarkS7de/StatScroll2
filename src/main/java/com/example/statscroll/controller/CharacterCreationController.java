package com.example.statscroll.controller;

import com.example.statscroll.dao.CharactersDAO;
import com.example.statscroll.model.Characters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CharacterCreationController {
    @FXML private Button previousButton;
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
    @FXML private TextField nameField;
    @FXML private TextField classField;
    @FXML private TextField subclassField;
    @FXML private TextField alignmentField;
    @FXML private TextField backgroundField;
    @FXML private TextField raceField;
    @FXML private TextField multiclassField;
    @FXML private Spinner<Integer> levelSpinner;
    @FXML private Button nextButton;

    @FXML private TextField ageField;
    @FXML private TextField heightField;
    @FXML private TextField weightField;
    @FXML private TextField eyesField;
    @FXML private TextField hairField;
    @FXML private TextField skinField;

    CharactersDAO charactersDAO = new CharactersDAO();

    @FXML
    public void initialize() {
        // Statistiche base (1–20)
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
    }


    public void handleNextClick(ActionEvent actionEvent) throws IOException {
        Characters character = new Characters(
                Session.getUserId(),
                nameField.getText(),
                classField.getText(),
                subclassField.getText(),
                multiclassField.getText(),
                levelSpinner.getValue(),
                raceField.getText(),
                backgroundField.getText(),
                alignmentField.getText(),
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
        charactersDAO.save(character);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handlePreviousClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}