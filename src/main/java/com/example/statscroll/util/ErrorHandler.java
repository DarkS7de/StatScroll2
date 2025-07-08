package com.example.statscroll.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

/**
 * Classe utilitaria per la gestione centralizzata degli errori nell'applicazione
 */
public class ErrorHandler {

    /**
     * Mostra un messaggio di errore in un Label
     * @param errorLabel Il componente Label dove mostrare l'errore
     * @param message Il messaggio di errore
     */
    public static void showError(Label errorLabel, String message) {
        if (errorLabel != null) {
            errorLabel.setText(message);
            errorLabel.setStyle("-fx-text-fill: #d32f2f; -fx-font-weight: bold;");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Mostra un messaggio di successo in un Label
     * @param errorLabel Il componente Label dove mostrare il messaggio
     * @param message Il messaggio di successo
     */
    public static void showSuccess(Label errorLabel, String message) {
        if (errorLabel != null) {
            errorLabel.setText(message);
            errorLabel.setStyle("-fx-text-fill: #388e3c; -fx-font-weight: bold;");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Resetta lo stato di un Label usato per i messaggi
     * @param errorLabel Il componente Label da resettare
     */
    public static void resetErrorLabel(Label errorLabel) {
        if (errorLabel != null) {
            errorLabel.setText("");
            errorLabel.setVisible(false);
        }
    }

    /**
     * Mostra una finestra di dialogo per errori gravi
     * @param title Il titolo della finestra
     * @param header Il testo dell'header
     * @param content Il contenuto del messaggio
     */
    public static void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Mostra una finestra di dialogo informativa
     * @param title Il titolo della finestra
     * @param header Il testo dell'header
     * @param content Il contenuto del messaggio
     */
    public static void showInfoDialog(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}