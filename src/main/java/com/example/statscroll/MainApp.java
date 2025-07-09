package com.example.statscroll;

import com.example.statscroll.dao.UsersDAO;
import com.example.statscroll.dice.DiceRoller;
import com.example.statscroll.gui.LoginPage;
import javafx.application.Application;

public class MainApp {
    private static DiceRoller diceRoller;

    public static void main(String[] args) {
        new UsersDAO(); // inizializza DB
        diceRoller = new DiceRoller(); // inizializza il dice roller
        Application.launch(LoginPage.class, args); // avvia GUI
    }

    public static DiceRoller getDiceRoller() {
        return diceRoller;
    }
}