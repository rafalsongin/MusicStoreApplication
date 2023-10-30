package com.example.javafxendassignment.services;

import com.example.javafxendassignment.database.DatabaseAccess;
import com.example.javafxendassignment.model.DatabaseSharedModel;

import java.io.*;

public class FileService {
    private final String filepath = "database.dat";

    public void saveDatabase() {
        DatabaseAccess database = DatabaseSharedModel.getDatabase();

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filepath))) {

            objectOutputStream.writeObject(database);

        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found: " + filepath);
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

        File file = new File(filepath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filepath))) {
            database = (DatabaseAccess) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }

        DatabaseSharedModel.setDatabase(database);
        System.out.println("Data loaded successfully");
    }
}