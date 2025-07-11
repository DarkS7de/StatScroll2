package com.example.statscroll.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class WikiPageController {

    @FXML private ListView<String> categoriesListView;
    @FXML private TextArea contentTextArea;

    @FXML
    public void initialize() {
        // Popola la lista delle categorie
        categoriesListView.getItems().addAll(
                "Mechanics",
                "Game Introduction",
                "Classes",
                "Races",
                "Weapons",
                "Armors",
                "Spells"
        );

        // Aggiungi listener per la selezione
        categoriesListView.setOnMouseClicked(this::handleCategorySelection);
    }

    private void handleCategorySelection(MouseEvent event) {
        String selectedCategory = categoriesListView.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            // Mostra contenuto basato sulla categoria selezionata
            String content = getWikiContent(selectedCategory);
            contentTextArea.setText(content);
        }
    }

    private String getWikiContent(String category) {
        // Questo è un contenuto di esempio, puoi espanderlo
        switch (category) {
            case "Mechanics":
                return "Game mechanics information...\n\nBasic rules and how to play.";
            case "Game Introduction":
                return "Welcome to the game!\n\nThis is a fantasy RPG...";
            case "Classes":
                return "Available classes:\n- Warrior\n- Mage\n- Thief\n- Cleric\n- Ranger\n- Sorcerer";
            case "Races":
                return "Playable races:\n- Human\n- Elf\n- Dwarf\n- Halfling\n- Half-Elf\n- Tiefling";
            case "Weapons":
                return "Weapons list:\n- Sword\n- Axe\n- Bow\n- Staff\n- Dagger";
            case "Armors":
                return "Armor types:\n- Light\n- Medium\n- Heavy";
            case "Spells":
                return "Spell categories:\n- Fire\n- Ice\n- Healing\n- Illusion";
            default:
                return "Select a category to view its content.";
        }
    }
}