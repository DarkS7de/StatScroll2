package com.example.statscroll.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuPageController {

    @FXML
    private void handleMyCharacters(ActionEvent event) {
        loadScene(event, "/fxml/myCharacters.fxml");
    }

    @FXML
    private void handleCreateCharacter(ActionEvent event) {
        loadScene(event, "/fxml/CharacterCreation.fxml");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        loadScene(event, "/fxml/login.fxml");
    }

    private void loadScene(ActionEvent event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
