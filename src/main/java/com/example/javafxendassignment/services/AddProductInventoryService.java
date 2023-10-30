package com.example.javafxendassignment.services;

import com.example.javafxendassignment.database.DatabaseAccess;
import com.example.javafxendassignment.model.DatabaseSharedModel;
import com.example.javafxendassignment.model.Product;

public class AddProductInventoryService {

    private final DatabaseAccess database;

    public AddProductInventoryService() {
        database = DatabaseSharedModel.getDatabase();
    }

    public void addProductToInventory(Product product) {
        database.addProduct(product);
    }


}
