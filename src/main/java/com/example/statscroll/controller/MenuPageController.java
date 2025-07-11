package com.example.statscroll.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuPageController {

    @FXML
    private void handleMyCharacters(ActionEvent event) {
        loadScene(event, "/fxml/myCharacters.fxml");
    }

    @FXML
    private void handleCreateCharacter(ActionEvent event) {  // Aggiunto ActionEvent come parametro
        try {
            // Carica il file FXML dalla cartella resources
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterCreationPage1.fxml"));
            Parent root = loader.load();

            // Ottiene la finestra corrente
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load character creation page", e.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();
            showAlert("Error", "Resource not found", "Character creation FXML file not found");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        loadScene(event, "/fxml/login.fxml");
    }

    @FXML
    private void handleDiceRoller(ActionEvent event) {
        try {
            // Carica il file FXML del dice roller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/diceRoller.fxml"));
            Parent root = loader.load();

            // Crea una nuova finestra
            Stage diceRollerStage = new Stage();
            diceRollerStage.setTitle("D&D Dice Roller");
            diceRollerStage.setScene(new Scene(root));
            diceRollerStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load Dice Roller", e.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();
            showAlert("Error", "Resource not found", "Dice Roller FXML file not found");
        }
    }

    private void loadScene(ActionEvent event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Loading Error", "Failed to load scene", "Could not load: " + fxmlPath);
        } catch (NullPointerException e) {
            e.printStackTrace();
            showAlert("Resource Error", "File not found", "FXML file not found: " + fxmlPath);
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
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
}