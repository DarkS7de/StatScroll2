package com.example.statscroll;

import com.example.statscroll.dao.UsersDAO;
import com.example.statscroll.gui.LoginPage;
import javafx.application.Application;

public class MainApp {
    public static void main(String[] args) {
        new UsersDAO(); // inizializza DB
        Application.launch(LoginPage.class, args); // avvia GUI
    }
}

