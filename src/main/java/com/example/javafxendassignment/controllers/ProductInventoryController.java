package com.example.javafxendassignment.controllers;

import com.example.javafxendassignment.MusicShopApplication;
import com.example.javafxendassignment.model.Product;
import com.example.javafxendassignment.services.ProductInventoryService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ProductInventoryController {
    private static final int APP_WIDTH = 600;
    private static final int APP_HEIGHT = 272;
    @FXML
    public Button addProductButton;
    @FXML
    public Button deleteProductButton;
    @FXML
    public Button editProductButton;
    @FXML
    public TableView productsTableView;
    @FXML
    public TableColumn stockTableColumn;
    @FXML
    public TableColumn nameTableColumn;
    @FXML
    public TableColumn categoryTableColumn;
    @FXML
    public TableColumn priceTableColumn;
    @FXML
    public TableColumn descriptionTableColumn;
    @FXML
    public Label noProductSelectedErrorMessage;
    @FXML
    public Label noProductAddedErrorMessage;
    @FXML
    public Button importProductsButton;
    private ProductInventoryService productInventoryService;

    @FXML
    public void initialize() {
        productInventoryService = new ProductInventoryService();
        showTableViewData();
    }

    @FXML
    public void clickAddProductButton() throws IOException {
        openWindow();
    }

    @FXML
    public void clickEditProductButton() throws IOException {
        isTableViewEmpty();

        if (productsTableView.getSelectionModel().getSelectedItem() != null) {
            Product selectedProduct = (Product) productsTableView.getSelectionModel().getSelectedItem();
            openWindow(selectedProduct);
        } else {
            showMessage(noProductSelectedErrorMessage);
        }
    }

    @FXML
    public void clickDeleteProductButton() {
        isTableViewEmpty();

        if (productsTableView.getSelectionModel().getSelectedItem() != null) {
            productInventoryService.deleteProduct((String) nameTableColumn.getCellData(productsTableView.getSelectionModel().getSelectedIndex()));
            productsTableView.getItems().remove(productsTableView.getSelectionModel().getSelectedItem());
        } else {
            showMessage(noProductSelectedErrorMessage);
        }
        refreshProductsTableView();
    }

    @FXML
    public void clickImportProductsButton(MouseEvent mouseEvent) {
        productInventoryService.importProducts();
        refreshProductsTableView();
    }

    private void showTableViewData() {
        productInventoryService.showTableViewData(productsTableView,
                stockTableColumn,
                nameTableColumn,
                categoryTableColumn,
                priceTableColumn,
                descriptionTableColumn);
    }

    // open add-product-inventory-view.fxml window
    private void openWindow() throws IOException {
        Scene scene = getSceneLoaded();

        Stage stage = new Stage();
        stage.setTitle("Add product");
        stage.setScene(scene);
        stage.show();
    }

    // open edit-product-inventory-view.fxml window
    private void openWindow(Product product) throws IOException {
        Scene scene = getSceneLoaded(product);

        Stage stage = new Stage();
        stage.setTitle("Edit product");
        stage.setScene(scene);
        stage.show();
    }

    // load add-product-inventory-view.fxml
    private Scene getSceneLoaded() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MusicShopApplication.class.getResource("add-product-inventory-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), APP_WIDTH, APP_HEIGHT);

        AddProductInventoryController addProductInventoryController = fxmlLoader.getController();
        addProductInventoryController.initialize(this);

        return scene;
    }

    // load edit-product-inventory-view.fxml
    private Scene getSceneLoaded(Product product) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MusicShopApplication.class.getResource("edit-product-inventory-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), APP_WIDTH, APP_HEIGHT);

        EditProductController editProductController = fxmlLoader.getController();
        editProductController.initialize(this, product);

        return scene;
    }

    private void isTableViewEmpty() {
        if (productsTableView.getItems().isEmpty()) {
            showMessage(noProductAddedErrorMessage);
        }
    }

    public void refreshProductsTableView() {
        showTableViewData();
        productsTableView.refresh();
    }

    private void showMessage(Label label) {
        List<Label> labels = List.of(noProductSelectedErrorMessage, noProductAddedErrorMessage);
        for (Label l : labels) {
            l.setVisible(l.equals(label));
        }
    }
}