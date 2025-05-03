package com.example.statscroll.model;

import java.util.Objects;

public class CharacterWeapons {
    private int id;
    private int character_id;
    private int weapon_id;

    public CharacterWeapons(int id, int character_id, int weapon_id) {
        this.id = id;
        this.character_id = character_id;
        this.weapon_id = weapon_id;
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

    public int getWeapon_id() {
        return weapon_id;
    }

    public void setWeapon_id(int weapon_id) {
        this.weapon_id = weapon_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CharacterWeapons that = (CharacterWeapons) o;
        return id == that.id && character_id == that.character_id && weapon_id == that.weapon_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, character_id, weapon_id);
    }

    @Override
    public String toString() {
        return "CharacterWeapons{" +
                "id=" + id +
                ", character_id=" + character_id +
                ", weapon_id=" + weapon_id +
                '}';
    }
}
