package com.example.javafxendassignment;

import com.example.javafxendassignment.controllers.LoginController;
import com.example.javafxendassignment.services.FileService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MusicShopApplication extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the login view
        FXMLLoader loginLoader = new FXMLLoader(MusicShopApplication.class.getResource("login-view.fxml"));

        int appWidth = 440;
        int appHeight = 340;

        // Set the login scene
        Scene loginScene = new Scene(loginLoader.load(), appWidth, appHeight);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();

        FileService fileService = new FileService();
        fileService.loadDatabase();

        LoginController controller = loginLoader.getController();
        controller.initialize();
    }
}

