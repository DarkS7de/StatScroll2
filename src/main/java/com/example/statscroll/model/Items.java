package com.example.statscroll.model;

import java.util.Objects;

public class Items {
    private int id;
    private String name;
    private String type1;
    private String type2;
    private int weight;
    private int price;

    public Items(int id, String name, String type1, String type2, int weight, int price) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.weight = weight;
        this.price = price;
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

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return id == items.id && weight == items.weight && price == items.price && Objects.equals(name, items.name) && Objects.equals(type1, items.type1) && Objects.equals(type2, items.type2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type1, type2, weight, price);
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }
}
