package com.example.statscroll.controller;

import com.example.statscroll.model.Characters;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe per la gestione della sessione utente e dei dati temporanei
 */
public class Session {
    private static final Logger LOGGER = Logger.getLogger(Session.class.getName());

    private static Integer userId;          // ID utente loggato
    private static String username;         // Username per visualizzazione
    private static Characters character;    // Personaggio selezionato
    private static boolean isAdmin = false; // Flag per permessi admin

    // Blocca l'istanziazione della classe
    private Session() {}

    /* Metodi per la gestione dell'utente */

    public static void loginUser(Integer id, String username, boolean admin) {
        validateId(id);
        userId = id;
        setUsername(username);
        isAdmin = admin;
        LOGGER.log(Level.INFO, "Utente loggato - ID: {0}, Username: {1}", new Object[]{id, username});
    }

    public static void logout() {
        LOGGER.log(Level.INFO, "Logout utente - ID: {0}", userId);
        clear();
    }

    public static Integer getUserId() {
        validateSession();
        return userId;
    }

    public static String getUsername() {
        validateSession();
        return username;
    }

    public static boolean isAdmin() {
        validateSession();
        return isAdmin;
    }

    /* Metodi per la gestione del personaggio */

    public static void setCurrentCharacter(Characters character) {
        if (character == null) {
            throw new IllegalArgumentException("Il personaggio non può essere null");
        }
        Session.character = character;
        LOGGER.log(Level.INFO, "Personaggio impostato - ID: {0}, Nome: {1}",
                new Object[]{character.getId(), character.getName()});
    }

    public static Characters getCurrentCharacter() {
        if (character == null) {
            throw new IllegalStateException("Nessun personaggio selezionato");
        }
        return character;
    }

    public static boolean hasCharacterSelected() {
        return character != null;
    }

    /* Metodi di utilità */

    public static boolean isLoggedIn() {
        return userId != null;
    }

    public static void clear() {
        userId = null;
        username = null;
        character = null;
        isAdmin = false;
    }

    /* Metodi di validazione */

    private static void validateSession() {
        if (!isLoggedIn()) {
            throw new IllegalStateException("Nessun utente loggato");
        }
    }

    private static void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID utente non valido");
        }
    }

    private static void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username non valido");
        }
        Session.username = username.trim();
    }
}