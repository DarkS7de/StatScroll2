package com.example.statscroll.dice;

import java.util.ArrayList;
import java.util.List;

public class DiceRollLogger {
    private final List<DiceRoll> rollHistory;

    public DiceRollLogger() {
        this.rollHistory = new ArrayList<>();
    }

    public void logRoll(DiceRoll roll) {
        rollHistory.add(roll);
        System.out.println(roll); // Stampa anche nella console
    }

    public List<DiceRoll> getHistory() {
        return new ArrayList<>(rollHistory);
    }

    public void clearHistory() {
        rollHistory.clear();
    }

    public void printFullHistory() {
        System.out.println("\n=== Dice Roll History ===");
        for (DiceRoll roll : rollHistory) {
            System.out.println(roll);
        }
        System.out.println("=======================");
    }
}