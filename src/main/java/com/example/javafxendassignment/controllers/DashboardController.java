package com.example.javafxendassignment.controllers;

import com.example.javafxendassignment.model.User;
import com.example.javafxendassignment.model.UserSharedModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardController {
    public Label welcomeLabel;
    public Label roleLabel;
    public Label currentDateTimeLabel;

    public void initialize() {
        User currentUser = UserSharedModel.getLoggedInUser();

        setNameAndRole(currentUser);
        setDateTimeTracker();
    }

    private void setNameAndRole(User currentUser) {
        if (currentUser != null) {
            welcomeLabel.setText("Welcome, " + currentUser.getFirstName() + " " + currentUser.getLastName() + "!");
            roleLabel.setText("Role: " + currentUser.getRole());
        } else {
            welcomeLabel.setText("Welcome!");
            roleLabel.setText("Role: Unknown");
        }
    }

    private void setDateTimeTracker() {
        setDateAndTime();

        // Set up a timeline to update the date and time label every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> setDateAndTime()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void setDateAndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(formatter);
        currentDateTimeLabel.setText(formattedDateTime);
    }
}