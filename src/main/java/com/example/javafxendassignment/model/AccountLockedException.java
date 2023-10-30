package com.example.javafxendassignment.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AccountLockedException extends Exception {
    public AccountLockedException(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your account has been locked. \nPlease contact the administrator for more information.", ButtonType.OK);
        alert.setTitle("Exception");
        alert.setHeaderText("Account locked!");
        alert.showAndWait();

        System.exit(0);
    }

}

