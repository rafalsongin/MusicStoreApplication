package com.example.javafxendassignment.model;

import java.io.Serializable;

public class OrderListProduct implements Serializable {
    private final double singleItemPrice;
    private int quantity;
    private String name;
    private String category;

    public OrderListProduct(int quantity, String name, String category, double singleItemPrice) {
        this.quantity = quantity;
        this.name = name;
        this.category = category;
        this.singleItemPrice = singleItemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public double getFullPrice() {
        return singleItemPrice * quantity;
    }
}

