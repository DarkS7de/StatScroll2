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

public class LoginPageController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final UsersDAO usersDAO = new UsersDAO();

    @FXML
    private void onLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            ErrorHandler.showError(errorLabel, "Inserisci username e password");
            return;
        }

        UsersDAO usersDAO = new UsersDAO();
        Users user = usersDAO.login(username, password);

        if (user != null) {
            Session.loginUser(user.getId(), user.getUsername(), user.isAdmin());
            ErrorHandler.showSuccess(errorLabel, "Login riuscito!");
            try {
                navigateToMenu();
            } catch (IOException e) {
                ErrorHandler.showError(errorLabel, "Errore nel caricamento del menu.");
                e.printStackTrace();
            }
        } else {
            ErrorHandler.showError(errorLabel, "Credenziali non valide");
        }
    }

    private void navigateToMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void onRegister(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            ErrorHandler.showError(errorLabel, "Errore nel caricamento della pagina di registrazione.");
            e.printStackTrace();
        }
    }
}