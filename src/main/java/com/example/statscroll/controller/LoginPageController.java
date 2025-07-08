package com.example.statscroll.controller;

import com.example.statscroll.dao.UsersDAO;
import com.example.statscroll.model.Users;
import com.example.statscroll.util.ErrorHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class LoginPageController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final UsersDAO usersDAO = new UsersDAO();

    @FXML
    private void onLogin(ActionEvent event) {
        ErrorHandler.resetErrorLabel(errorLabel);

        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            ErrorHandler.showError(errorLabel, "Inserisci username e password.");
            return;
        }

        try {
            if (usersDAO.login(username, password)) {
                Session.setUserId(username);
                ErrorHandler.showSuccess(errorLabel, "Login riuscito!");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                ErrorHandler.showError(errorLabel, "Credenziali non valide.");
            }
        } catch (Exception e) {
            ErrorHandler.showErrorDialog("Errore", "Errore durante il login",
                    "Si è verificato un errore durante l'accesso. Riprovare.");
            e.printStackTrace();
        }
    }
    @FXML
    private void onRegister(ActionEvent event) {
        try {
            // Carica la scena di registrazione
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Errore nel caricamento della pagina di registrazione.");
        }
    }
}