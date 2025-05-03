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
    private int str, dex, con, intel, wis, cha;
    private boolean inspiration;
    private int profbonus;
    private int maxhp, currenthp, temphp; //chiedi a fra perchè
    private String totalhitdice, hitdice;
    private int spellsavedc, spellattackbonus;
    private String age, eyes, hair, skin, height, weight; //dire a fra che abbiamo i capelli
    private String portrait_url;

    public Characters(int id, String user_id, String name, String char_class, String subclass, String multiclass, int level, String race, String background, String alignment, int initiative, int speed, int exp, int str, int dex, int con, int intel, int wis, int cha, boolean inspiration, int profbonus, int maxhp, int currenthp, int temphp, String totalhitdice, String hitdice, int spellsavedc, int spellattackbonus, String age, String eyes, String hair, String skin, String height, String weight, String portrait_url) {
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

    public int getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getChar_class() {
        return char_class;
    }

    public String getSubclass() {
        return subclass;
    }

    public String getMulticlass() {
        return multiclass;
    }

    public int getLevel() {
        return level;
    }

    public String getRace() {
        return race;
    }

    public String getBackground() {
        return background;
    }

    public String getAlignment() {
        return alignment;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getSpeed() {
        return speed;
    }

    public int getExp() {
        return exp;
    }

    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getCon() {
        return con;
    }

    public int getIntel() {
        return intel;
    }

    public int getWis() {
        return wis;
    }

    public int getCha() {
        return cha;
    }

    public boolean isInspiration() {
        return inspiration;
    }

    public int getProfbonus() {
        return profbonus;
    }

    public int getMaxhp() {
        return maxhp;
    }

    public int getCurrenthp() {
        return currenthp;
    }

    public int getTemphp() {
        return temphp;
    }

    public String getTotalhitdice() {
        return totalhitdice;
    }

    public String getHitdice() {
        return hitdice;
    }

    public int getSpellsavedc() {
        return spellsavedc;
    }

    public int getSpellattackbonus() {
        return spellattackbonus;
    }

    public String getAge() {
        return age;
    }

    public String getEyes() {
        return eyes;
    }

    public String getHair() {
        return hair;
    }

    public String getSkin() {
        return skin;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getPortrait_url() {
        return portrait_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChar_class(String char_class) {
        this.char_class = char_class;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public void setMulticlass(String multiclass) {
        this.multiclass = multiclass;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public void setCha(int cha) {
        this.cha = cha;
    }

    public void setInspiration(boolean inspiration) {
        this.inspiration = inspiration;
    }

    public void setProfbonus(int profbonus) {
        this.profbonus = profbonus;
    }

    public void setMaxhp(int maxhp) {
        this.maxhp = maxhp;
    }

    public void setCurrenthp(int currenthp) {
        this.currenthp = currenthp;
    }

    public void setTemphp(int temphp) {
        this.temphp = temphp;
    }

    public void setTotalhitdice(String totalhitdice) {
        this.totalhitdice = totalhitdice;
    }

    public void setHitdice(String hitdice) {
        this.hitdice = hitdice;
    }

    public void setSpellsavedc(int spellsavedc) {
        this.spellsavedc = spellsavedc;
    }

    public void setSpellattackbonus(int spellattackbonus) {
        this.spellattackbonus = spellattackbonus;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setPortrait_url(String portrait_url) {
        this.portrait_url = portrait_url;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Characters that = (Characters) o;
        return id == that.id && level == that.level && initiative == that.initiative && speed == that.speed && exp == that.exp && str == that.str && dex == that.dex && con == that.con && intel == that.intel && wis == that.wis && cha == that.cha && inspiration == that.inspiration && profbonus == that.profbonus && maxhp == that.maxhp && currenthp == that.currenthp && temphp == that.temphp && spellsavedc == that.spellsavedc && spellattackbonus == that.spellattackbonus && Objects.equals(user_id, that.user_id) && Objects.equals(name, that.name) && Objects.equals(char_class, that.char_class) && Objects.equals(subclass, that.subclass) && Objects.equals(multiclass, that.multiclass) && Objects.equals(race, that.race) && Objects.equals(background, that.background) && Objects.equals(alignment, that.alignment) && Objects.equals(totalhitdice, that.totalhitdice) && Objects.equals(hitdice, that.hitdice) && Objects.equals(age, that.age) && Objects.equals(eyes, that.eyes) && Objects.equals(hair, that.hair) && Objects.equals(skin, that.skin) && Objects.equals(height, that.height) && Objects.equals(weight, that.weight) && Objects.equals(portrait_url, that.portrait_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, name, char_class, subclass, multiclass, level, race, background, alignment, initiative, speed, exp, str, dex, con, intel, wis, cha, inspiration, profbonus, maxhp, currenthp, temphp, totalhitdice, hitdice, spellsavedc, spellattackbonus, age, eyes, hair, skin, height, weight, portrait_url);
    }

    @Override
    public String toString() {
        return "com.example.statscroll.model.Characters{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", char_class='" + char_class + '\'' +
                ", subclass='" + subclass + '\'' +
                ", multiclass='" + multiclass + '\'' +
                ", level=" + level +
                ", race='" + race + '\'' +
                ", background='" + background + '\'' +
                ", alignment='" + alignment + '\'' +
                ", initiative=" + initiative +
                ", speed=" + speed +
                ", exp=" + exp +
                ", str=" + str +
                ", dex=" + dex +
                ", con=" + con +
                ", intel=" + intel +
                ", wis=" + wis +
                ", cha=" + cha +
                ", inspiration=" + inspiration +
                ", profbonus=" + profbonus +
                ", maxhp=" + maxhp +
                ", currenthp=" + currenthp +
                ", temphp=" + temphp +
                ", totalhitdice='" + totalhitdice + '\'' +
                ", hitdice='" + hitdice + '\'' +
                ", spellsavedc=" + spellsavedc +
                ", spellattackbonus=" + spellattackbonus +
                ", age='" + age + '\'' +
                ", eyes='" + eyes + '\'' +
                ", hair='" + hair + '\'' +
                ", skin='" + skin + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", portrait_url='" + portrait_url + '\'' +
                '}';
    }
}
