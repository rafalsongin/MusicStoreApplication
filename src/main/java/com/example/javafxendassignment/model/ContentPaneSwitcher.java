package com.example.javafxendassignment.model;

import com.example.javafxendassignment.MusicShopApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ContentPaneSwitcher {

    @FXML
    AnchorPane contentPane;

    public void loadContent(String filename) {
        try {
            // clear previous content
            contentPane.getChildren().clear();

            // load new content
            FXMLLoader fxmlLoader = new FXMLLoader(MusicShopApplication.class.getResource(filename));
            AnchorPane content = fxmlLoader.load();
            contentPane.getChildren().add(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadDashboard(AnchorPane contentPane) {
        this.contentPane = contentPane;
        loadContent("dashboard-view.fxml");
    }

    public void loadOrderView(AnchorPane contentPane) {
        this.contentPane = contentPane;
        loadContent("create-order-view.fxml");
    }

    public void loadInventoryView(AnchorPane contentPane) {
        this.contentPane = contentPane;
        loadContent("product-inventory-view.fxml");
    }

    public void loadOrderHistoryView(AnchorPane contentPane) {
        this.contentPane = contentPane;
        loadContent("order-history-view.fxml");
    }
}
