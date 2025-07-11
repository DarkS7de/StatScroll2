package com.example.statscroll.model;

import java.util.Objects;

public class Characters {
    private int id;
    private String user_id;
    private String name;
    private String char_class;
    private int level;
    private String race;
    private int initiative;
    private int speed;
    private int str;
    private int dex;
    private int con;
    private int intel;
    private int wis;
    private int cha;
    private boolean inspiration;
    private int profbonus;
    private int maxhp;
    private String hitdice;
    private String age;
    private String eyes;
    private String hair;
    private String skin;
    private String height;
    private String weight;
    private String inventory;
    private String portrait_url;

    // Costruttori
    public Characters() {
        // Valori di default
        this.level = 1;
        this.profbonus = 2;
        this.speed = 30;
    }

    // Costruttore minimale per creazione base
    public Characters(String name, String char_class, String race) {
        this();
        this.name = name;
        this.char_class = char_class;
        this.race = race;
    }

    // Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getChar_class() { return char_class; }
    public void setChar_class(String char_class) { this.char_class = char_class; }

    public int getLevel() { return level; }
    public void setLevel(int level) {
        this.level = level;
        // Aggiorna il bonus di competenza in base al livello
        this.profbonus = calculateProficiencyBonus();
    }

    public String getRace() { return race; }
    public void setRace(String race) { this.race = race; }

    public int getInitiative() { return initiative; }
    public void setInitiative(int initiative) { this.initiative = initiative; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public int getStr() { return str; }
    public void setStr(int str) { this.str = str; }

    public int getDex() { return dex; }
    public void setDex(int dex) { this.dex = dex; }

    public int getCon() { return con; }
    public void setCon(int con) { this.con = con; }

    public int getIntel() { return intel; }
    public void setIntel(int intel) { this.intel = intel; }

    public int getWis() { return wis; }
    public void setWis(int wis) { this.wis = wis; }

    public int getCha() { return cha; }
    public void setCha(int cha) { this.cha = cha; }

    public boolean isInspiration() { return inspiration; }
    public void setInspiration(boolean inspiration) { this.inspiration = inspiration; }

    public int getProfbonus() { return profbonus; }
    public void setProfbonus(int profbonus) { this.profbonus = profbonus; }

    public int getMaxhp() { return maxhp; }
    public void setMaxhp(int maxhp) { this.maxhp = maxhp; }

    public String getHitdice() { return hitdice; }
    public void setHitdice(String hitdice) { this.hitdice = hitdice; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getEyes() { return eyes; }
    public void setEyes(String eyes) { this.eyes = eyes; }

    public String getHair() { return hair; }
    public void setHair(String hair) { this.hair = hair; }

    public String getSkin() { return skin; }
    public void setSkin(String skin) { this.skin = skin; }

    public String getHeight() { return height; }
    public void setHeight(String height) { this.height = height; }

    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }

    public String getInventory() { return inventory; }
    public void setInventory(String inventory) { this.inventory = inventory; }

    public String getPortrait_url() { return portrait_url; }
    public void setPortrait_url(String portrait_url) { this.portrait_url = portrait_url; }

    // Metodi di utilità
    private int calculateProficiencyBonus() {
        return 2 + (level - 1) / 4;
    }

    public int getAbilityModifier(int abilityScore) {
        return (abilityScore - 10) / 2;
    }

    // Metodi sovrascritti
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characters that = (Characters) o;
        return id == that.id &&
                Objects.equals(user_id, that.user_id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, name);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", class='" + char_class + '\'' +
                ", level=" + level +
                ", race='" + race + '\'' +
                '}';
    }

    public String toListViewString() {
        return String.format("%s (Lvl %d %s %s)", name, level, race, char_class);
    }
}