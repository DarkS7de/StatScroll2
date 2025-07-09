package com.example.statscroll.model;

import java.util.Objects;

public class Characters {
    private int id;
    private String user_id;
    private String name;
    private String char_class;
    private String subclass;
    private String multiclass;
    private int level;
    private String race;
    private String background;
    private String alignment;
    private int initiative;
    private int speed;
    private int exp;
    private int str;
    private int dex;
    private int con;
    private int intel;
    private int wis;
    private int cha;
    private boolean inspiration;
    private int profbonus;
    private int maxhp;
    private int currenthp;
    private int temphp;
    private String totalhitdice;
    private String hitdice;
    private int spellsavedc;
    private int spellattackbonus;
    private String age;
    private String eyes;
    private String hair;
    private String skin;
    private String height;
    private String weight;
    private String portrait_url;

    // Costruttori
    public Characters() {}

    public Characters(int id, String user_id, String name, String char_class, String subclass,
                      String multiclass, int level, String race, String background,
                      String alignment, int initiative, int speed, int exp, int str,
                      int dex, int con, int intel, int wis, int cha, boolean inspiration,
                      int profbonus, int maxhp, int currenthp, int temphp, String totalhitdice,
                      String hitdice, int spellsavedc, int spellattackbonus, String age,
                      String eyes, String hair, String skin, String height, String weight,
                      String portrait_url) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.char_class = char_class;
        this.subclass = subclass;
        this.multiclass = multiclass;
        this.level = level;
        this.race = race;
        this.background = background;
        this.alignment = alignment;
        this.initiative = initiative;
        this.speed = speed;
        this.exp = exp;
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
        this.inspiration = inspiration;
        this.profbonus = profbonus;
        this.maxhp = maxhp;
        this.currenthp = currenthp;
        this.temphp = temphp;
        this.totalhitdice = totalhitdice;
        this.hitdice = hitdice;
        this.spellsavedc = spellsavedc;
        this.spellattackbonus = spellattackbonus;
        this.age = age;
        this.eyes = eyes;
        this.hair = hair;
        this.skin = skin;
        this.height = height;
        this.weight = weight;
        this.portrait_url = portrait_url;
    }

    // Getter e Setter completi
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getChar_class() { return char_class; }
    public void setChar_class(String char_class) { this.char_class = char_class; }

    public String getSubclass() { return subclass; }
    public void setSubclass(String subclass) { this.subclass = subclass; }

    public String getMulticlass() { return multiclass; }
    public void setMulticlass(String multiclass) { this.multiclass = multiclass; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public String getRace() { return race; }
    public void setRace(String race) { this.race = race; }

    public String getBackground() { return background; }
    public void setBackground(String background) { this.background = background; }

    public String getAlignment() { return alignment; }
    public void setAlignment(String alignment) { this.alignment = alignment; }

    public int getInitiative() { return initiative; }
    public void setInitiative(int initiative) { this.initiative = initiative; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public int getExp() { return exp; }
    public void setExp(int exp) { this.exp = exp; }

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

    public int getCurrenthp() { return currenthp; }
    public void setCurrenthp(int currenthp) { this.currenthp = currenthp; }

    public int getTemphp() { return temphp; }
    public void setTemphp(int temphp) { this.temphp = temphp; }

    public String getTotalhitdice() { return totalhitdice; }
    public void setTotalhitdice(String totalhitdice) { this.totalhitdice = totalhitdice; }

    public String getHitdice() { return hitdice; }
    public void setHitdice(String hitdice) { this.hitdice = hitdice; }

    public int getSpellsavedc() { return spellsavedc; }
    public void setSpellsavedc(int spellsavedc) { this.spellsavedc = spellsavedc; }

    public int getSpellattackbonus() { return spellattackbonus; }
    public void setSpellattackbonus(int spellattackbonus) { this.spellattackbonus = spellattackbonus; }

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

    public String getPortrait_url() { return portrait_url; }
    public void setPortrait_url(String portrait_url) { this.portrait_url = portrait_url; }

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
                '}';
    }

    public String toListViewString() {
        return String.format("%s (Lvl %d %s)", name, level, char_class);
    }
}