package com.example.statscroll.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.statscroll.model.Characters;

import java.io.IOException;
import java.net.URL;

public class CharacterCreationPage2Controller {

    private Characters character;

    // Componenti FXML
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
        try {
            // Verifica che tutti i componenti siano stati iniettati
            if (strSpinner == null || dexSpinner == null || conSpinner == null ||
                    intelSpinner == null || wisSpinner == null || chaSpinner == null ||
                    profBonSpinner == null || initiativeSpinner == null || maxHPSpinner == null ||
                    hitDiceField == null || inspirationCheckBox == null || speedSpinner == null ||
                    portraitUrlField == null || inventoryField == null || previousButton == null ||
                    confirmButton == null || pdfExportButton == null || wikiButton == null) {
                throw new IllegalStateException("Uno o più componenti FXML non sono stati iniettati correttamente");
            }

            // Inizializzazione degli spinner
            initSpinner(strSpinner, 1, 30, 10);
            initSpinner(dexSpinner, 1, 30, 10);
            initSpinner(conSpinner, 1, 30, 10);
            initSpinner(intelSpinner, 1, 30, 10);
            initSpinner(wisSpinner, 1, 30, 10);
            initSpinner(chaSpinner, 1, 30, 10);
            initSpinner(profBonSpinner, 2, 6, 2);
            initSpinner(initiativeSpinner, -5, 10, 0);
            initSpinner(maxHPSpinner, 1, 500, 10);
            initSpinner(speedSpinner, 0, 100, 30);

            // Configurazione dei pulsanti
            previousButton.setOnAction(e -> handlePreviousClick());
            confirmButton.setOnAction(e -> handleConfirmClick());
            pdfExportButton.setOnAction(e -> handlePDFExport());
            wikiButton.setOnAction(e -> openWiki());

        } catch (Exception e) {
            showAlert("Errore di Inizializzazione", "Errore durante l'inizializzazione della pagina", e.getMessage());
            e.printStackTrace();
        }
    }

    private void initSpinner(Spinner<Integer> spinner, int min, int max, int initial) {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initial);
        spinner.setValueFactory(valueFactory);
        spinner.setEditable(true);

        // Validazione input manuale
        spinner.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            try {
                if (!newValue.isEmpty()) {
                    int value = Integer.parseInt(newValue);
                    if (value < min || value > max) {
                        spinner.getEditor().setText(oldValue);
                    }
                }
            } catch (NumberFormatException e) {
                spinner.getEditor().setText(oldValue);
            }
        });
    }

    private void handlePreviousClick() {
        try {
            URL fxmlUrl = getClass().getResource("/fxml/characterCreationPage1.fxml");
            if (fxmlUrl == null) {
                throw new IOException("File FXML non trovato");
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            CharacterCreationPage1Controller page1Controller = new CharacterCreationPage1Controller();
            loader.setController(page1Controller);
            Parent root = loader.load();

            Stage stage = (Stage) previousButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            showAlert("Errore", "Impossibile tornare alla pagina precedente", e.getMessage());
        }
    }

    private void handleConfirmClick() {
        if (!validateFields()) {
            return;
        }

        saveCharacterData();
        navigateToMenu();
    }

    private void handlePDFExport() {
        saveCharacterData();
        // Implementa qui la logica di esportazione PDF
        showAlert("Esportazione PDF", "PDF generato", "Il personaggio è stato esportato in PDF");
    }

    private boolean validateFields() {
        if (hitDiceField.getText().isEmpty()) {
            showAlert("Validazione", "Campo mancante", "Inserisci i dadi vita (Hit Dice)");
            return false;
        }
        return true;
    }

    private void saveCharacterData() {
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
        character.setInventory(inventoryField.getText());

        System.out.println("Dati personaggio salvati: " + character);
    }

    private void navigateToMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Errore", "Impossibile tornare al menu", e.getMessage());
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
            showAlert("Errore", "Impossibile aprire la wiki", e.getMessage());
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}