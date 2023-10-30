package com.example.javafxendassignment.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Order implements Serializable {

    private final int id;
    private final String dateOfOrder;
    private final List<OrderListProduct> orderedProducts;
    private String name;
    private double totalPrice;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerPhoneNumber;

    public Order(List<OrderListProduct> orderedProducts,
                 String customerFirstName,
                 String customerLastName,
                 String customerEmail,
                 String customerPhoneNumber) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        this.dateOfOrder = dateFormat.format(new Date());
        this.orderedProducts = new ArrayList<>(orderedProducts);
        setTotalPrice();
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;

        Random random = new Random();
        this.id = random.nextInt(100000);
        this.name = "Order #" + id;
    }

    public int getId() {
        return id;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalPrice() {
        for (OrderListProduct product : orderedProducts) {
            totalPrice += product.getFullPrice();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public List<OrderListProduct> getOrderedListProducts() {
        return orderedProducts;
    }

}
