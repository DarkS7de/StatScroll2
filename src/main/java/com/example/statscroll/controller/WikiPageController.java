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
        switch (category) {
            case "Mechanics":
                return "CORE GAME MECHANICS:\n\n" +
                        "• Ability Scores: Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma (range 1–20).\n" +
                        "• Inspiration: A bonus reward for roleplaying (grants advantage on rolls).\n" +
                        "• Hit Dice: Determines HP recovery during short/long rests.\n" +
                        "• Max HP: Class base + Constitution modifier.\n" +
                        "• Level: Progresses from 1 to 20 (unlocks class features).\n" +
                        "• Speed: Base movement (e.g., 30 ft. for most races).";

            case "Game Introduction":
                return "WELCOME TO DUNGEONS & DRAGONS!\n\n" +
                        "D&D is a tabletop RPG where you:\n" +
                        "• Create unique heroes (combining races/classes)\n" +
                        "• Explore fantasy worlds with friends\n" +
                        "• Solve puzzles, battle monsters, and complete quests\n\n" +
                        "Roll dice to determine outcomes, and let your imagination guide the story!";

            case "Classes":
                return "AVAILABLE CLASSES:\n\n" +
                        "• Paladin: Holy warrior with divine magic and combat skills.\n" +
                        "• Fighter (Guerriero): Master of weapons and armor.\n" +
                        "• Wizard (Mago): Arcane spellcaster focused on study.\n" +
                        "• Druid: Shapeshifter and nature magic wielder.\n" +
                        "• Cleric (Chierico): Divine healer or smiter.\n" +
                        "• Rogue (Ladro): Stealthy damage-dealer with skills.";

            case "Races":
                return "PLAYABLE RACES:\n\n" +
                        "• Dwarf (Nano): Tough, resilient, and skilled in craftsmanship.\n" +
                        "• Elf: Graceful with magic affinity and keen senses.\n" +
                        "• Half-Elf (Mezzelfo): Charismatic hybrid of human and elf.\n" +
                        "• Tiefling: Infernal heritage with innate magic.\n" +
                        "• Human (Umano): Adaptable and versatile.\n" +
                        "• Halfling: Nimble and lucky smallfolk.";

            case "Weapons":
                return "BASIC WEAPONS:\n\n" +
                        "MELEE:\n- Longsword (1d8 slashing)\n- Dagger (1d4 piercing)\n- Great Axe (1d12 slashing)\n\n" +
                        "RANGED:\n- Shortbow (1d6 piercing)\n- Crossbow (1d10 piercing)\n\n" +
                        "SIMPLE:\n- Staff (1d6 bludgeoning)\n- Spear (1d6 piercing)";

            case "Armors":
                return "ARMOR TYPES:\n\n" +
                        "LIGHT:\n- Leather (+1 AC)\n- Padded (stealth-friendly)\n\n" +
                        "MEDIUM:\n- Chain shirt (+3 AC)\n- Scale mail (+4 AC)\n\n" +
                        "HEAVY:\n- Plate armor (+8 AC, max protection)\n\n" +
                        "SHIELDS: +2 AC (any type)";

            case "Spells":
                return "FAMOUS SPELLS:\n\n" +
                        "• Fireball (3rd-level): 8d6 fire damage in 20-ft radius.\n" +
                        "• Cure Wounds: Heals 1d8 + spellcasting modifier.\n" +
                        "• Eldritch Blast: Warlock's force-damage cantrip.\n" +
                        "• Magic Missile: 3 auto-hit darts (1d4+1 each).\n" +
                        "• Invisibility: Makes target unseen for 1 hour.";

            default:
                return "Select a category to view its content.";
        }
    }
}