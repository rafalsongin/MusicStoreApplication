package com.example.javafxendassignment.controllers;

import com.example.javafxendassignment.Application;
import com.example.javafxendassignment.model.ContentPaneSwitcher;
import com.example.javafxendassignment.model.Role;
import com.example.javafxendassignment.model.User;
import com.example.javafxendassignment.model.UserSharedModel;
import com.example.javafxendassignment.services.FileService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.List;

public class MainWindowController {
    @FXML
    public Button productInventoryButton;
    @FXML
    public Button orderHistoryButton;
    @FXML
    public Button createOrderButton;
    @FXML
    public Button dashboardButton;
    @FXML
    public Button logoutButton;
    @FXML
    public AnchorPane contentPane;

    private ContentPaneSwitcher contentPaneSwitcher;

    public void initialize() {
        this.contentPaneSwitcher = new ContentPaneSwitcher();
        User currentUser = UserSharedModel.getLoggedInUser();

        setMenuButtonsAccess(currentUser);
    }

    @FXML
    public void openProductInventoryButton() {
        contentPaneSwitcher.loadInventoryView(contentPane);
        setButtonActiveColor(productInventoryButton);
    }

    @FXML
    public void openOrderHistoryButton() {
        contentPaneSwitcher.loadOrderHistoryView(contentPane);
        setButtonActiveColor(orderHistoryButton);
    }

    @FXML
    public void openCreateOrderButton() {
        contentPaneSwitcher.loadOrderView(contentPane);
        setButtonActiveColor(createOrderButton);
    }

    @FXML
    public void openDashboardButton() {
        contentPaneSwitcher.loadDashboard(contentPane);
        setButtonActiveColor(dashboardButton);
    }

    @FXML
    public void onClickLogoutButton(MouseEvent mouseEvent) throws IOException {
        FileService fileService = new FileService();
        fileService.saveDatabase();

        UserSharedModel.setLoggedInUser(null);

        Alert alert = getLogoutAlert();
        if (alert.getResult() == ButtonType.YES) {
            openLoginWindow();
            closeWindow(mouseEvent);
        } else {
            alert.close();
        }
    }

    private static Alert getCloseAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to close the application?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert;
    }

    private static Alert getLogoutAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to logout of the current profile?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert;
    }

    private static void openLoginWindow() throws IOException {
        // open login-view.fxml and close main-window-view.fxml
        FXMLLoader loginLoader = new FXMLLoader(Application.class.getResource("login-view.fxml"));

        int appWidth = 440;
        int appHeight = 340;

        // Set the login scene
        Scene loginScene = new Scene(loginLoader.load(), appWidth, appHeight);

        Stage primaryStage = new Stage();
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();

        LoginController controller = loginLoader.getController();
        controller.initialize();
    }

    private void setMenuButtonsAccess(User currentUser) {
        if (currentUser != null) {
            if (currentUser.getRole().equals(Role.Sales)) {
                productInventoryButton.setDisable(true);
            } else if (currentUser.getRole().equals(Role.Manager)) {
                createOrderButton.setDisable(true);
            }
        }
    }

    public void initializeMainWindow(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-window-view.fxml"));
        int appWidth = 1120;
        int appHeight = 700;
        Scene scene = new Scene(fxmlLoader.load(), appWidth, appHeight);
        Stage stage = new Stage();
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
        contentPane = (AnchorPane) scene.lookup("#contentPane");

        // load dashboard content
        FXMLLoader fxmlContentLoader = new FXMLLoader(Application.class.getResource("dashboard-view.fxml"));
        AnchorPane content = fxmlContentLoader.load();
        contentPane.getChildren().add(content);

        // set close window request
        setOnCloseWindowRequest(mouseEvent, stage);
        closeWindow(mouseEvent);
    }

    private static void setOnCloseWindowRequest(MouseEvent mouseEvent, Stage stage) {
        FileService fileService = new FileService();
        stage.setOnCloseRequest((WindowEvent event) -> {
            Alert alert = getCloseAlert();
            if (alert.getResult() == ButtonType.YES) {
                ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
                fileService.saveDatabase();
            } else {
                alert.close();
            }
        });
    }

    private void setButtonActiveColor(Button button) {
        List<Button> buttons = List.of(productInventoryButton, orderHistoryButton, createOrderButton, dashboardButton);

        button.setStyle("-fx-background-color: #adc095");

        for (Button b : buttons) {
            if (b != button) {
                b.setStyle("-fx-background-color: #ccd9be");
            }
        }
    }

    public void closeWindow(MouseEvent mouseEvent) {
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
    }
}