package com.example.javafxendassignment.services;

import com.example.javafxendassignment.database.DatabaseAccess;
import com.example.javafxendassignment.model.DatabaseSharedModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileService {
    private static final String FILE_PATH = "database.dat";

    public void saveDatabase() {
        DatabaseAccess database = DatabaseSharedModel.getDatabase();

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {

            objectOutputStream.writeObject(database);

        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found: " + FILE_PATH);
        } catch (IOException ioe) {
            System.out.println("IO Exception: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
        System.out.println("Data saved successfully");
    }

    public void loadDatabase() throws FileNotFoundException {
        // loads default database if file loading fails
        DatabaseAccess database = new DatabaseAccess();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            database = (DatabaseAccess) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }

        DatabaseSharedModel.setDatabase(database);
        System.out.println("Data loaded successfully");
    }

    public void importProducts(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;

                if (lineNumber > 1) {
                    String[] productData = line.split(";");
                    String name = productData[0];
                    String category = productData[1];
                    String price = productData[2];
                    String description = productData[3];
                    String stock = productData[4];

                    ProductInventoryService productInventoryService = new ProductInventoryService();
                    productInventoryService.addProduct(name, category, price, description, stock);
                }
            }
        } catch (IOException e) {
            System.out.println("Error importing products: " + e.getMessage());
        }
    }
}