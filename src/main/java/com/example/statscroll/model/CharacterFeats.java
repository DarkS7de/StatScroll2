package com.example.statscroll.model;

import java.util.Objects;

public class CharacterFeats {
    private int id;
    private int character_id;
    private String feat_name;
    private String value;

    public CharacterFeats(int id, int character_id, String feat_name, String value) {
        this.id = id;
        this.character_id = character_id;
        this.feat_name = feat_name;
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

    public String getFeat_name() {
        return feat_name;
    }

    public void setFeat_name(String feat_name) {
        this.feat_name = feat_name;
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
        CharacterFeats that = (CharacterFeats) o;
        return id == that.id && character_id == that.character_id && Objects.equals(feat_name, that.feat_name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, character_id, feat_name, value);
    }

    @Override
    public String toString() {
        return "CharacterFeats{" +
                "id=" + id +
                ", character_id=" + character_id +
                ", feat_name='" + feat_name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
