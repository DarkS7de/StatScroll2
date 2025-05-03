package com.example.statscroll.model;

import java.util.Objects;

public class CharacterItems {
    private int id;
    private int character_id;
    private int item_id;
    private int quantity;

    public CharacterItems(int id, int character_id, int item_id, int quantity) {
        this.id = id;
        this.character_id = character_id;
        this.item_id = item_id;
        this.quantity = quantity;
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

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CharacterItems that = (CharacterItems) o;
        return id == that.id && character_id == that.character_id && item_id == that.item_id && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, character_id, item_id, quantity);
    }

    @Override
    public String toString() {
        return "CharacterItems{" +
                "id=" + id +
                ", character_id=" + character_id +
                ", item_id=" + item_id +
                ", quantity=" + quantity +
                '}';
    }
}
