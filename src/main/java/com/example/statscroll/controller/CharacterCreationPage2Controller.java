package com.example.statscroll.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.statscroll.model.Characters;

import java.io.IOException;

public class CharacterCreationPage2Controller {

    private Characters character;

    @FXML private Spinner<Integer> strSpinner;
    @FXML private Spinner<Integer> dexSpinner;
    @FXML private Spinner<Integer> conSpinner;
    @FXML private Spinner<Integer> intelSpinner;
    @FXML private Spinner<Integer> wisSpinner;
    @FXML private Spinner<Integer> chaSpinner;
    @FXML private Spinner<Integer> profBonSpinner;
    @FXML private Spinner<Integer> initiativeSpinner;
    @FXML private Spinner<Integer> maxHPSpinner;
    @FXML private TextField hitDiceField;
    @FXML private CheckBox inspirationCheckBox;
    @FXML private Spinner<Integer> speedSpinner;
    @FXML private TextField portraitUrlField;
    @FXML private TextArea inventoryField;
    @FXML private Button previousButton;
    @FXML private Button confirmButton;
    @FXML private Button pdfExportButton;
    @FXML private Button wikiButton;

    public CharacterCreationPage2Controller(Characters character) {
        this.character = character;
    }

    @FXML
    public void initialize() {
        // Imposta i valori di default per gli spinner
        strSpinner.getValueFactory().setValue(10);
        dexSpinner.getValueFactory().setValue(10);
        conSpinner.getValueFactory().setValue(10);
        intelSpinner.getValueFactory().setValue(10);
        wisSpinner.getValueFactory().setValue(10);
        chaSpinner.getValueFactory().setValue(10);
        profBonSpinner.getValueFactory().setValue(2);
        initiativeSpinner.getValueFactory().setValue(0);
        maxHPSpinner.getValueFactory().setValue(10);
        speedSpinner.getValueFactory().setValue(30);

        // Configura il pulsante Wiki
        wikiButton.setOnAction(event -> openWiki());
    }

    @FXML
    private void handlePreviousClick() {
        try {
            // Torna alla prima pagina mantenendo i dati
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/statscroll/view/characterCreationPage1.fxml"));
            CharacterCreationPage1Controller page1Controller = new CharacterCreationPage1Controller();
            loader.setController(page1Controller);
            Parent root = loader.load();

            Stage stage = (Stage) previousButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleConfirmClick() {
        // Salva i dati nella classe Characters
        character.setStr(strSpinner.getValue());
        character.setDex(dexSpinner.getValue());
        character.setCon(conSpinner.getValue());
        character.setIntel(intelSpinner.getValue());
        character.setWis(wisSpinner.getValue());
        character.setCha(chaSpinner.getValue());
        character.setProfbonus(profBonSpinner.getValue());
        character.setInitiative(initiativeSpinner.getValue());
        character.setMaxhp(maxHPSpinner.getValue());
        character.setHitdice(hitDiceField.getText());
        character.setInspiration(inspirationCheckBox.isSelected());
        character.setSpeed(speedSpinner.getValue());
        character.setPortrait_url(portraitUrlField.getText());

        // Salva il personaggio nel database
        saveCharacter();

        // Torna al menu principale
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePDFExport() {
        // Prima salva i dati
        handleConfirmClick();

        // Poi esporta in PDF
        // Implementa la logica di esportazione PDF qui
        System.out.println("Esportazione PDF del personaggio: " + character.getName());
    }

    private void saveCharacter() {
        // Implementa la logica per salvare il personaggio nel database o file
        System.out.println("Personaggio salvato: " + character);
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