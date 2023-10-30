package com.example.javafxendassignment.model;

import java.io.Serializable;

public class OrderListProduct implements Serializable {
    private int quantity;
    private String name;
    private String category;
    private final double singleItemPrice;

    public OrderListProduct(int quantity, String name, String category, double singleItemPrice) {
        this.quantity = quantity;
        this.name = name;
        this.category = category;
        this.singleItemPrice = singleItemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getFullPrice() {
        return singleItemPrice * quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

