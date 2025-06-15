package com.example.statscroll.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPageController {

    @FXML
    private void handleMyCharacters(ActionEvent event) {
        loadScene(event, "/com/example/statscroll/view/MyCharacters.fxml");
    }

    @FXML
    private void handleCreateCharacter(ActionEvent event) {
        loadScene(event, "/com/example/statscroll/view/Sheet.fxml");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        loadScene(event, "/com/example/statscroll/view/MenuPage.fxml");
    }

    private void loadScene(ActionEvent event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // puoi sostituire con logger se vuoi
        }
    }
}
