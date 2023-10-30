package com.example.javafxendassignment.controllers;

import com.example.javafxendassignment.model.AccountLockedException;
import com.example.javafxendassignment.model.User;
import com.example.javafxendassignment.model.UserSharedModel;
import com.example.javafxendassignment.services.LoginService;
import com.example.javafxendassignment.services.PasswordVerifyingService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class LoginController {
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button loginButton;
    @FXML
    public Label passwordInputIncorrectInput;
    @FXML
    public Label passwordInfoMessage;

    private final LoginService loginService;
    private int incorrectInputCount;

    public LoginController() {
        loginService = new LoginService();
        incorrectInputCount = 0;
    }

    public void initialize() {
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean isValidPassword = checkPasswordValidity(newValue);
            loginButton.setDisable(!isValidPassword);

            if (!isValidPassword) {
                showMessage(passwordInfoMessage);
            } else {
                passwordInfoMessage.setVisible(false);
            }
        });
    }

    @FXML
    protected void onLoginButtonClick(MouseEvent mouseEvent) throws IOException, AccountLockedException {
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();

        User user = loginService.getUserByUsername(inputUsername);

        if (user == null || !PasswordVerifyingService.checkPassword(inputPassword, user.getPasswordHash())) {
            incorrectInputCount++;
            if (incorrectInputCount == 4) {
                throw new AccountLockedException();
            }
            showMessage(passwordInputIncorrectInput);


        } else {
            UserSharedModel.setLoggedInUser(user);

            loginService.startMainWindow(mouseEvent);
        }
    }

    protected boolean checkPasswordValidity(String password) {
        boolean hasLetters = false;
        boolean hasDigits = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigits = true;
            } else if (Character.isLetter(c)) {
                hasLetters = true;
            } else {
                hasSpecial = true;
            }
        }
        return password.length() > 7 && (hasLetters && hasDigits && hasSpecial);
    }

    private void showMessage(Label label) {
        List<Label> labels = List.of(passwordInputIncorrectInput, passwordInfoMessage);
        for (Label l : labels) {
            l.setVisible(l.equals(label));
        }
    }
}