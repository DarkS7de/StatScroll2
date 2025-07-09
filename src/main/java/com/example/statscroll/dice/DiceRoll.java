package com.example.statscroll.dice;

public class DiceRoll {
    private final String notation;
    private final int result;
    private final String description;

    public DiceRoll(String notation, int result, String description) {
        this.notation = notation;
        this.result = result;
        this.description = description;
    }

    // Getters
    public String getNotation() { return notation; }
    public int getResult() { return result; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return description + ": " + notation + " = " + result;
    }
}