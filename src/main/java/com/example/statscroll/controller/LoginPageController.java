package com.example.statscroll.controller;

import com.example.statscroll.dao.UsersDAO;
import com.example.statscroll.model.Users;
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
    private void onLogin(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (usersDAO.login(username, password)) {
            Session.setUserId(username);
            errorLabel.setText("Login riuscito!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            errorLabel.setText("Credenziali non valide.");
        }
    }

    @FXML
    private void onRegister(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Errore nel caricamento della pagina di registrazione.");
        }
    }
}
