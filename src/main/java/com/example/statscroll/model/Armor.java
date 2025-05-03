package com.example.statscroll.model;

import java.util.Objects;

public class Armor {
    private int id;
    private String name;
    private int base_ac;
    private int dex_cap;
    private String type;

    public Armor(int id, String name, int base_ac, int dex_cap, String type) {
        this.id = id;
        this.name = name;
        this.base_ac = base_ac;
        this.dex_cap = dex_cap;
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

    public int getBase_ac() {
        return base_ac;
    }

    public void setBase_ac(int base_ac) {
        this.base_ac = base_ac;
    }

    public int getDex_cap() {
        return dex_cap;
    }

    public void setDex_cap(int dex_cap) {
        this.dex_cap = dex_cap;
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
        Armor armor = (Armor) o;
        return id == armor.id && base_ac == armor.base_ac && dex_cap == armor.dex_cap && Objects.equals(name, armor.name) && Objects.equals(type, armor.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, base_ac, dex_cap, type);
    }

    @Override
    public String toString() {
        return "Armor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", base_ac=" + base_ac +
                ", dex_cap=" + dex_cap +
                ", type='" + type + '\'' +
                '}';
    }
}
