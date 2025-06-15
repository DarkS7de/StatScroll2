package com.example.statscroll.controller;

import com.example.statscroll.model.Characters;

public class Session {
    private static String user_id;
    private static Characters characters;

    public static void setUserId(String id) {
        user_id = id;
    }

    public static void setCharacters(Characters characters) {
        Session.characters = characters;
    }

    public static Characters getCharacters() {
        return characters;
    }

    public static String getUserId() {
        return user_id;
    }

    public static void clear(){
        user_id = null;
    }
}

