package com.example.statscroll.model;

import java.util.Objects;

public class CharacterSpells {
    private int id;
    private int character_id;
    private int spell_id;
    private boolean prepared;

    public CharacterSpells(int id, int character_id, int spell_id, boolean prepared) {
        this.id = id;
        this.character_id = character_id;
        this.spell_id = spell_id;
        this.prepared = prepared;
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

    public int getSpell_id() {
        return spell_id;
    }

    public void setSpell_id(int spell_id) {
        this.spell_id = spell_id;
    }

    public boolean isPrepared() {
        return prepared;
    }

    public void setPrepared(boolean prepared) {
        this.prepared = prepared;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CharacterSpells that = (CharacterSpells) o;
        return id == that.id && character_id == that.character_id && spell_id == that.spell_id && prepared == that.prepared;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, character_id, spell_id, prepared);
    }

    @Override
    public String toString() {
        return "CharacterSpells{" +
                "id=" + id +
                ", character_id=" + character_id +
                ", spell_id=" + spell_id +
                ", prepared=" + prepared +
                '}';
    }
}
