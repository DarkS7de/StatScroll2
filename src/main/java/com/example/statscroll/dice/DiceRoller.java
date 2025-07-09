package com.example.statscroll.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiceRoller {
    private final Random random;
    private final DiceRollLogger logger;
    private final List<SavedRoll> savedRolls;

    public DiceRoller() {
        this.random = new Random();
        this.logger = new DiceRollLogger();
        this.savedRolls = new ArrayList<>();
    }

    // Metodo base per tirare un dado
    public int rollDice(int faces) {
        if (faces <= 0) {
            throw new IllegalArgumentException("Il dado deve avere almeno 1 faccia");
        }
        return random.nextInt(faces) + 1;
    }

    // Metodi specifici per ogni dado di D&D
    public DiceRoll rollD4(String description) {
        int result = rollDice(4);
        DiceRoll roll = new DiceRoll("d4", result, description);
        logger.logRoll(roll);
        return roll;
    }

    public DiceRoll rollD6(String description) {
        int result = rollDice(6);
        DiceRoll roll = new DiceRoll("d6", result, description);
        logger.logRoll(roll);
        return roll;
    }

    public DiceRoll rollD8(String description) {
        int result = rollDice(8);
        DiceRoll roll = new DiceRoll("d8", result, description);
        logger.logRoll(roll);
        return roll;
    }

    public DiceRoll rollD10(String description) {
        int result = rollDice(10);
        DiceRoll roll = new DiceRoll("d10", result, description);
        logger.logRoll(roll);
        return roll;
    }

    public DiceRoll rollD12(String description) {
        int result = rollDice(12);
        DiceRoll roll = new DiceRoll("d12", result, description);
        logger.logRoll(roll);
        return roll;
    }

    public DiceRoll rollD20(String description) {
        int result = rollDice(20);
        DiceRoll roll = new DiceRoll("d20", result, description);
        logger.logRoll(roll);
        return roll;
    }

    public DiceRoll rollD100(String description) {
        int result = rollDice(100);
        DiceRoll roll = new DiceRoll("d100", result, description);
        logger.logRoll(roll);
        return roll;
    }

    // Metodo per tirare più dadi con modificatore
    public DiceRoll rollMultipleDice(int faces, int quantity, int modifier, String description) {
        int total = modifier;
        StringBuilder details = new StringBuilder();

        for (int i = 0; i < quantity; i++) {
            int roll = rollDice(faces);
            total += roll;
            if (i > 0) details.append(" + ");
            details.append(roll);
        }

        if (modifier != 0) {
            details.append(" + ").append(modifier);
        }

        String notation = quantity + "d" + faces + (modifier != 0 ? (modifier > 0 ? "+" : "") + modifier : "");
        DiceRoll diceRoll = new DiceRoll(notation, total, description);
        logger.logRoll(diceRoll);
        return diceRoll;
    }

    // Metodo per interpretare la notazione D&D (es. "2d6+3")
    public DiceRoll rollFromNotation(String notation, String description) {
        Pattern pattern = Pattern.compile("(\\d*)d(\\d+)([+-]\\d+)?");
        Matcher matcher = pattern.matcher(notation.toLowerCase());

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Notazione non valida: " + notation);
        }

        int quantity = matcher.group(1).isEmpty() ? 1 : Integer.parseInt(matcher.group(1));
        int faces = Integer.parseInt(matcher.group(2));
        int modifier = matcher.group(3) == null ? 0 : Integer.parseInt(matcher.group(3));

        return rollMultipleDice(faces, quantity, modifier, description);
    }

    // Classe per i tiri salvati
    private static class SavedRoll {
        private final String name;
        private final String notation;
        private final String description;

        public SavedRoll(String name, String notation, String description) {
            this.name = name;
            this.notation = notation;
            this.description = description;
        }

        public String getName() { return name; }
        public String getNotation() { return notation; }
        public String getDescription() { return description; }
    }

    // Metodi per gestire i tiri salvati
    public void saveRoll(String name, String notation, String description) {
        savedRolls.removeIf(r -> r.getName().equalsIgnoreCase(name));
        savedRolls.add(new SavedRoll(name, notation, description));
    }

    public DiceRoll rollSaved(String name) {
        return savedRolls.stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(r -> rollFromNotation(r.getNotation(), r.getDescription()))
                .orElseThrow(() -> new IllegalArgumentException("Tiro salvato non trovato: " + name));
    }

    public List<String> getSavedRollNames() {
        return savedRolls.stream().map(SavedRoll::getName).toList();
    }

    // Metodo per accedere al logger
    public DiceRollLogger getLogger() {
        return logger;
    }
}