package com.example.statscroll.controller;

import com.example.statscroll.util.ErrorHandler;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;
import java.util.Date;
import java.io.IOException;

public class RegisterController {
    private final UsersDAO usersDAO = new UsersDAO();

    @FXML private TextField emailField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            ErrorHandler.showErrorDialog("Error", "Navigation error", "Cannot load login page.");
        }
    }

    @FXML
    private void onRegister(ActionEvent event) {
        ErrorHandler.resetErrorLabel(errorLabel);

        String email = emailField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Basic validation
        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            ErrorHandler.showError(errorLabel, "Compila tutti i campi.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            ErrorHandler.showError(errorLabel, "Le password non coincidono.");
            return;
        }

        try {
            if (usersDAO.usernameExists(username)) {
                ErrorHandler.showError(errorLabel, "Nome utente già in uso.");
                return;
            }

            if (usersDAO.emailExists(email)) {
                ErrorHandler.showError(errorLabel, "Email già registrata.");
                return;
            }

            Users user = new Users(username, password, email);
            user.setCreatedAt(new Date());
            usersDAO.save(user);

            ErrorHandler.showSuccess(errorLabel, "Registrazione completata!");

            // Redirect to login after delay
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
                    Stage stage = (Stage) emailField.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException ex) {
                    ErrorHandler.showErrorDialog("Errore", "Errore di navigazione",
                            "Impossibile caricare la pagina di login.");
                }
            });
            pause.play();

        } catch (Exception e) {
            ErrorHandler.showErrorDialog("Errore", "Errore di registrazione",
                    "Si è verificato un errore imprevisto.");
            e.printStackTrace();
        }
    }
}