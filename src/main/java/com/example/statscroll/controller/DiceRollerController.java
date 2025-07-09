package com.example.statscroll.controller;

import com.example.statscroll.MainApp;
import com.example.statscroll.dice.DiceRoller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class DiceRollerController {
    private final DiceRoller diceRoller = MainApp.getDiceRoller();

    @FXML private VBox diceButtonsContainer;
    @FXML private TextField customNotationField;
    @FXML private TextField descriptionField;
    @FXML private TextField saveNameField;
    @FXML private ListView<String> savedRollsList;
    @FXML private TextArea historyArea;

    @FXML
    public void initialize() {
        setupDiceButtons();
        refreshSavedRolls();
        updateHistory();
    }

    private void setupDiceButtons() {
        String[] diceTypes = {"d4", "d6", "d8", "d10", "d12", "d20", "d100"};
        for (String dice : diceTypes) {
            Button btn = new Button("Roll " + dice);
            btn.setOnAction(e -> rollBasicDice(dice));
            btn.setMaxWidth(Double.MAX_VALUE);
            diceButtonsContainer.getChildren().add(btn);
        }
    }

    private void rollBasicDice(String diceType) {
        String description = "Rolled " + diceType;
        switch (diceType) {
            case "d4" -> diceRoller.rollD4(description);
            case "d6" -> diceRoller.rollD6(description);
            case "d8" -> diceRoller.rollD8(description);
            case "d10" -> diceRoller.rollD10(description);
            case "d12" -> diceRoller.rollD12(description);
            case "d20" -> diceRoller.rollD20(description);
            case "d100" -> diceRoller.rollD100(description);
        }
        updateHistory();
    }

    @FXML
    private void handleCustomRoll() {
        String notation = customNotationField.getText();
        String description = descriptionField.getText().isEmpty() ?
                "Custom roll: " + notation : descriptionField.getText();

        try {
            diceRoller.rollFromNotation(notation, description);
            updateHistory();
        } catch (IllegalArgumentException e) {
            showAlert("Invalid Notation", e.getMessage());
        }
    }

    @FXML
    private void handleSaveRoll() {
        String name = saveNameField.getText();
        String notation = customNotationField.getText();
        String description = descriptionField.getText();

        if (!name.isEmpty() && !notation.isEmpty()) {
            diceRoller.saveRoll(name, notation, description.isEmpty() ? notation : description);
            refreshSavedRolls();
            showAlert("Success", "Roll saved successfully!");
        } else {
            showAlert("Error", "Name and notation are required");
        }
    }

    @FXML
    private void handleRollSaved() {
        String selected = savedRollsList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            diceRoller.rollSaved(selected);
            updateHistory();
        }
    }

    @FXML
    private void refreshSavedRolls() {
        savedRollsList.getItems().setAll(diceRoller.getSavedRollNames());
    }

    private void updateHistory() {
        StringBuilder historyText = new StringBuilder("=== Roll History ===\n");
        diceRoller.getLogger().getHistory().forEach(roll ->
                historyText.append(roll.toString()).append("\n"));
        historyArea.setText(historyText.toString());
        historyArea.setScrollTop(Double.MAX_VALUE); // Auto-scroll to bottom
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void closeWindow() {
        historyArea.getScene().getWindow().hide();
    }
}