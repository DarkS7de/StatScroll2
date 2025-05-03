package com.example.statscroll.model;

import java.util.Objects;

public class CharacterTraits {
    private int id;
    private int character_id;
    private String type;
    private String value;

    public CharacterTraits(int id, int character_id, String type, String value) {
        this.id = id;
        this.character_id = character_id;
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(int character_id) {
        this.character_id = character_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CharacterTraits that = (CharacterTraits) o;
        return id == that.id && character_id == that.character_id && Objects.equals(type, that.type) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, character_id, type, value);
    }

    @Override
    public String toString() {
        return "CharacterTraits{" +
                "id=" + id +
                ", character_id=" + character_id +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
