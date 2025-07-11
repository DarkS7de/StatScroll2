package com.example.statscroll.controller;

import com.example.statscroll.dao.CharactersDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.statscroll.model.Characters;
import com.example.statscroll.util.ErrorHandler;

import java.io.IOException;
import java.net.URL;

public class CharacterCreationPage2Controller {

    private CharactersDAO charactersDAO = new CharactersDAO();
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
    @FXML private TextArea inventoryField;
    @FXML private Button previousButton;
    @FXML private Button confirmButton;
    @FXML private Button wikiButton;

    public CharacterCreationPage2Controller(Characters character) {
        this.character = character;
    }

    @FXML
    public void initialize() {
        try {
            // Verifica che il character esista
            if (character == null) {
                character = new Characters();
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
        saveCharacterData(); // Salva i dati prima di tornare indietro

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterCreationPage1.fxml"));
            Parent root = loader.load();

            // Passa i dati alla pagina 1
            CharacterCreationPage1Controller page1Controller = loader.getController();
            page1Controller.setCharacter(character);

            Stage stage = (Stage) previousButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Errore", "Impossibile tornare alla pagina precedente", e.getMessage());
        }
    }

    private void openWiki() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/wikiPage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Wiki Personaggi");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Errore nel caricamento della pagina","Errore", "Impossibile aprire la pagina Wiki");
        }
    }

    private void handleConfirmClick() {
        if (!validateFields()) {
            return;
        }

        saveCharacterData();

        try {
            if (character.getId() == 0) {  // Se è un nuovo personaggio
                character.setUser_id(Session.getUserId().toString());
                charactersDAO.save(character);
                showAlert("Successo", "Personaggio creato", "Il personaggio è stato salvato con successo!");
            } else {
                charactersDAO.update(character);
                showAlert("Successo", "Personaggio aggiornato", "Il personaggio è stato aggiornato con successo!");
            }

            // Chiudi la finestra corrente e torna a MyCharacters
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/myCharacters.fxml"));
            Parent root = loader.load();

            // Ottieni il controller e forza il ricaricamento
            MyCharactersController controller = loader.getController();
            controller.loadCharactersFromDatabase();

            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            showAlert("Errore", "Salvataggio fallito", "Impossibile salvare il personaggio: " + e.getMessage());
            e.printStackTrace();
        }
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
        character.setInventory(inventoryField.getText());
    }

    private void navigateToMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Errore", "Impossibile tornare al menu", "Errore durante il caricamento del menu: " + e.getMessage());
            e.printStackTrace();
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