package com.example.javafxendassignment.controllers;

import com.example.javafxendassignment.model.OrderListProduct;
import com.example.javafxendassignment.model.Product;
import com.example.javafxendassignment.services.AddProductOrderService;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class AddProductOrderController {
    @FXML
    public TextField quantityTextField;
    @FXML
    public Button addToOrderButton;
    @FXML
    public Button cancelButton;
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
    public Label productSuccessfullyAddedMessage;
    @FXML
    public Label noProductSelectedErrorMessage;
    @FXML
    public Label quantityHigherThanStockErrorMessage;
    @FXML
    public Label quantityEmptyMessage;
    @FXML
    public Label quantityIncorrectErrorMessage;
    @FXML
    public TextField filterProductsTextField;

    private AddProductOrderService addProductOrderService;
    private CreateOrderController createOrderController;

    public void initialize(CreateOrderController createOrderController) {
        addProductOrderService = new AddProductOrderService();
        this.createOrderController = createOrderController;
        showTableViewData();

        filterProducts();
    }

    @FXML
    public void clickAddToOrderButton() {
        Product selectedProduct = (Product) productsTableView.getSelectionModel().getSelectedItem();
        if (!isValidInput(selectedProduct)) return;

        int quantity = Integer.parseInt(quantityTextField.getText());

        // deduct quantity from the 'stock' of the selected product and refresh it in the products table view
        selectedProduct.setStock(selectedProduct.getStock() - quantity);
        productsTableView.refresh();

        OrderListProduct orderListProduct = getOrderListProduct(quantity, selectedProduct);
        addProductOrderService.addOrderListProduct(orderListProduct);

        showMessage(productSuccessfullyAddedMessage);
        cancelButton.setText("Close");
    }

    @FXML
    public void clickCloseButton(MouseEvent mouseEvent) {
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide(); // close current window
        createOrderController.refreshOrderListProductsTableView();
    }

    private void filterProducts() {
        filterProductsTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 2) {
                List<Product> filteredProducts = addProductOrderService.filterProducts(newValue);
                showSearchedTableViewData(filteredProducts);
            } else {
                showTableViewData();
            }
        });
    }

    private void showTableViewData() {
        addProductOrderService.showTableViewData(productsTableView,
                stockTableColumn,
                nameTableColumn,
                categoryTableColumn,
                priceTableColumn,
                descriptionTableColumn);
    }

    private void showSearchedTableViewData(List<Product> searchedProducts) {
        addProductOrderService.showSearchedTableViewData(searchedProducts,
                productsTableView,
                stockTableColumn,
                nameTableColumn,
                categoryTableColumn,
                priceTableColumn,
                descriptionTableColumn);
    }

    private boolean isValidInput(Product selectedProduct) {
        if (selectedProduct != null) {
            if (quantityTextField.getText().isEmpty()) {
                showMessage(quantityEmptyMessage);
            } else if (Integer.parseInt(quantityTextField.getText()) > selectedProduct.getStock()) {
                showMessage(quantityHigherThanStockErrorMessage);
            } else if (Integer.parseInt(quantityTextField.getText()) <= 0) {
                showMessage(quantityIncorrectErrorMessage);
            } else {
                return true;
            }
        } else {
            showMessage(noProductSelectedErrorMessage);
        }
        return false;
    }

    private static OrderListProduct getOrderListProduct(int quantity, Product selectedProduct) {
        return new OrderListProduct(
                quantity,
                selectedProduct.getName(),
                selectedProduct.getCategory(),
                selectedProduct.getPrice()
        );
    }

    private void showMessage(Label label) {
        List<Label> labels = List.of(productSuccessfullyAddedMessage,
                noProductSelectedErrorMessage,
                quantityHigherThanStockErrorMessage,
                quantityEmptyMessage,
                quantityIncorrectErrorMessage);

        for (Label l : labels) {
            l.setVisible(l == label);
        }
    }

}
