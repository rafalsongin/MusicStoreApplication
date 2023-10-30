package com.example.javafxendassignment.services;

import com.example.javafxendassignment.controllers.MainWindowController;
import com.example.javafxendassignment.database.DatabaseAccess;
import com.example.javafxendassignment.model.DatabaseSharedModel;
import com.example.javafxendassignment.model.User;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginService {
    public void startMainWindow(MouseEvent mouseEvent) throws IOException {
        MainWindowController mainWindowController = new MainWindowController();
        mainWindowController.initializeMainWindow(mouseEvent);
    }

    public User getUserByUsername(String inputUsername) {
        DatabaseAccess database = DatabaseSharedModel.getDatabase();

        return database.getUserByUsername(inputUsername);
    }
}