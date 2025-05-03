package com.example.statscroll.model;

import java.util.Objects;

public class CharacterProficiencies {
    private int id;
    private int character_id;
    private int proficiency_id;
    private boolean expertise;

    public CharacterProficiencies(int id, int character_id, int proficiency_id, boolean expertise) {
        this.id = id;
        this.character_id = character_id;
        this.proficiency_id = proficiency_id;
        this.expertise = expertise;
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

    public int getProficiency_id() {
        return proficiency_id;
    }

    public void setProficiency_id(int proficiency_id) {
        this.proficiency_id = proficiency_id;
    }

    public boolean isExpertise() {
        return expertise;
    }

    public void setExpertise(boolean expertise) {
        this.expertise = expertise;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CharacterProficiencies that = (CharacterProficiencies) o;
        return id == that.id && character_id == that.character_id && proficiency_id == that.proficiency_id && expertise == that.expertise;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, character_id, proficiency_id, expertise);
    }

    @Override
    public String toString() {
        return "CharacterProficiencies{" +
                "id=" + id +
                ", character_id=" + character_id +
                ", proficiency_id=" + proficiency_id +
                ", expertise=" + expertise +
                '}';
    }
}
