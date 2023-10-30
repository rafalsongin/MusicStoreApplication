package com.example.javafxendassignment.model;

import com.example.javafxendassignment.database.DatabaseAccess;

public class DatabaseSharedModel {
    private static DatabaseAccess database;

    public static DatabaseAccess getDatabase() {
        return database;
    }

    public static void setDatabase(DatabaseAccess database) {
        DatabaseSharedModel.database = database;
    }
}
