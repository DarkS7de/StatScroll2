package com.example.statscroll.model;

import java.util.Objects;

public class CharacterArmor {
    private int id;
    private int character_id;
    private int armor_id;
    private boolean equipped;

    public CharacterArmor(int id, int character_id, int armor_id, boolean equipped) {
        this.id = id;
        this.character_id = character_id;
        this.armor_id = armor_id;
        this.equipped = equipped;
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

    public int getArmor_id() {
        return armor_id;
    }

    public void setArmor_id(int armor_id) {
        this.armor_id = armor_id;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CharacterArmor that = (CharacterArmor) o;
        return id == that.id && character_id == that.character_id && armor_id == that.armor_id && equipped == that.equipped;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, character_id, armor_id, equipped);
    }

    @Override
    public String toString() {
        return "CharacterArmor{" +
                "id=" + id +
                ", character_id=" + character_id +
                ", armor_id=" + armor_id +
                ", equipped=" + equipped +
                '}';
    }
}
