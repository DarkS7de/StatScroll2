package com.example.statscroll.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.example.statscroll.dao.UsersDAO;
import com.example.statscroll.model.Users;
import java.util.Date;
import java.io.IOException;
import java.sql.*;


public class RegisterController {
    private final UsersDAO usersDAO = new UsersDAO();

    @FXML private TextField emailField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;

    @FXML
    private void onRegister(ActionEvent event) {
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            errorLabel.setText("Le password non coincidono.");
            return;
        }

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Compila tutti i campi.");
            return;
        }

        try {
            Date now = new Date();
            Users user = new Users(username, password, email, now);
            usersDAO.save(user);

            // Vai alla login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            errorLabel.setText("Errore durante la registrazione.");
            e.printStackTrace();
        }
    }
}