package com.example.statscroll.controller;

import com.example.statscroll.dao.UsersDAO;
import com.example.statscroll.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Date;

public class LoginPageController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final UsersDAO usersDAO = new UsersDAO();

    @FXML
    private void onLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (usersDAO.login(username, password)) {
            errorLabel.setText("Login riuscito!");
            // TODO: Vai alla schermata principale
        } else {
            errorLabel.setText("Credenziali non valide.");
        }
    }

    @FXML
    private void onRegister(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Compila tutti i campi.");
            return;
        }

        Users user;
        user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail("email@fittizio.com"); // opzionale
        user.setCreated_at(new Date());

        usersDAO.save(user);
        errorLabel.setText("Registrazione completata.");
    }
}
