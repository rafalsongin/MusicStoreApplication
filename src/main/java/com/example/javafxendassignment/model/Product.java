package com.example.javafxendassignment.model;

import java.io.Serializable;

public class Product implements Serializable {

    private int stock;
    private String name;
    private String category;
    private String description;
    private double price;

    public Product(int stock, String name, String category, String description, double price) {
        this.stock = stock;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
