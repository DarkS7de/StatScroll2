package com.example.statscroll.model;

import java.util.Objects;

public class Spells {
    private int id;
    private String name;
    private int level;
    private String school;
    private String cast_time;
    private String range;
    private String duration;
    private boolean verbal, somatic, material;
    private String material_cost;
    private String damage;
    private String healing;
    private String description;

    public Spells(int id, String name, int level, String school, String cast_time, String range, String duration, boolean verbal, boolean somatic, boolean material, String material_cost, String damage, String healing, String description) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.school = school;
        this.cast_time = cast_time;
        this.range = range;
        this.duration = duration;
        this.verbal = verbal;
        this.somatic = somatic;
        this.material = material;
        this.material_cost = material_cost;
        this.damage = damage;
        this.healing = healing;
        this.description = description;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCast_time() {
        return cast_time;
    }

    public void setCast_time(String cast_time) {
        this.cast_time = cast_time;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isVerbal() {
        return verbal;
    }

    public void setVerbal(boolean verbal) {
        this.verbal = verbal;
    }

    public boolean isSomatic() {
        return somatic;
    }

    public void setSomatic(boolean somatic) {
        this.somatic = somatic;
    }

    public boolean isMaterial() {
        return material;
    }

    public void setMaterial(boolean material) {
        this.material = material;
    }

    public String getMaterial_cost() {
        return material_cost;
    }

    public void setMaterial_cost(String material_cost) {
        this.material_cost = material_cost;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getHealing() {
        return healing;
    }

    public void setHealing(String healing) {
        this.healing = healing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Spells spells = (Spells) o;
        return id == spells.id && level == spells.level && verbal == spells.verbal && somatic == spells.somatic && material == spells.material && Objects.equals(name, spells.name) && Objects.equals(school, spells.school) && Objects.equals(cast_time, spells.cast_time) && Objects.equals(range, spells.range) && Objects.equals(duration, spells.duration) && Objects.equals(material_cost, spells.material_cost) && Objects.equals(damage, spells.damage) && Objects.equals(healing, spells.healing) && Objects.equals(description, spells.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, school, cast_time, range, duration, verbal, somatic, material, material_cost, damage, healing, description);
    }

    @Override
    public String toString() {
        return "Spells{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", school='" + school + '\'' +
                ", cast_time='" + cast_time + '\'' +
                ", range='" + range + '\'' +
                ", duration='" + duration + '\'' +
                ", verbal=" + verbal +
                ", somatic=" + somatic +
                ", material=" + material +
                ", material_cost='" + material_cost + '\'' +
                ", damage='" + damage + '\'' +
                ", healing='" + healing + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
