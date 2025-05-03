package com.example.statscroll.model;

import java.util.Objects;

public class Weapons {
    private int id;
    private String name;
    private String damage;
    private String type;

    public Weapons(int id, String name, String damage, String type) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Weapons weapons = (Weapons) o;
        return id == weapons.id && Objects.equals(name, weapons.name) && Objects.equals(damage, weapons.damage) && Objects.equals(type, weapons.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, damage, type);
    }

    @Override
    public String toString() {
        return "Weapons{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", damage='" + damage + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
