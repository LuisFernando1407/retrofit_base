package com.retrofitbase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Product {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("description")
    @Expose
    private String description;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Override
    public String toString() {
        return
                "#ID: " + getId() +
                "<br>Nome: " + getName() +
                "<br>Preço: " + getPrice() +
                "<br>Descrição: " + getDescription()
        ;
    }
}