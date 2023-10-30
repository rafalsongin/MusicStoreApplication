package com.example.javafxendassignment.services;

import com.example.javafxendassignment.database.DatabaseAccess;
import com.example.javafxendassignment.model.DatabaseSharedModel;
import com.example.javafxendassignment.model.Product;

public class EditProductService {

    private final DatabaseAccess database;

    public EditProductService() {
        database = DatabaseSharedModel.getDatabase();
    }


    public void editProduct(Product productToEdit, Product editedProduct) {
        database.editProduct(productToEdit, editedProduct);
    }
}
