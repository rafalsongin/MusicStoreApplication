package com.example.javafxendassignment.controllers;

import com.example.javafxendassignment.Application;
import com.example.javafxendassignment.model.Order;
import com.example.javafxendassignment.model.OrderListProduct;
import com.example.javafxendassignment.services.CreateOrderService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CreateOrderController {
    @FXML
    public TextField firstNameTextField;
    @FXML
    public TextField lastNameTextField;
    @FXML
    public TextField emailAddressTextField;
    @FXML
    public TextField phoneNumberTextField;
    @FXML
    public Button addProductButton;
    @FXML
    public Button deleteProductButton;
    @FXML
    public Button createNewOrderButton;
    @FXML
    public TableView orderListProductsTableView;
    @FXML
    public TableColumn quantityTableColumn;
    @FXML
    public TableColumn nameTableColumn;
    @FXML
    public TableColumn categoryTableColumn;
    @FXML
    public TableColumn priceTableColumn;
    @FXML
    public Label productNotSelectedErrorMessage;
    @FXML
    public Label noProductToDeleteErrorMessage;
    @FXML
    public Label notAllFieldsFilledErrorMessage;
    @FXML
    public Label emailInputNotValidErrorMessage;
    @FXML
    public Label phoneNumberNotValidErrorMessage;
    @FXML
    public Label orderCreatedSuccessfullyMessage;
    @FXML
    public Label noProductAddedErrorMessage;

    private CreateOrderService createOrderService;

    public void initialize() {
        createOrderService = new CreateOrderService();
        showTableViewData();
    }

    @FXML
    public void clickAddProductButton() throws IOException {
        openWindow();
    }

    @FXML
    public void clickDeleteProductButton() {
        OrderListProduct selectedOrderListProduct = (OrderListProduct) orderListProductsTableView.getSelectionModel().getSelectedItem();

        if (selectedOrderListProduct != null) {
            int quantity = selectedOrderListProduct.getQuantity();
            createOrderService.deleteOrderListProductAndResetStock((String) nameTableColumn.getCellData(selectedOrderListProduct), quantity);
        } else if (orderListProductsTableView.getItems().isEmpty()) {
            showMessage(noProductToDeleteErrorMessage);
        } else {
            showMessage(productNotSelectedErrorMessage);
        }

        refreshOrderListProductsTableView();
    }

    @FXML
    public void clickCreateNewOrderButton() {
        if (!isValidInput()) return;

        Alert alert = getAlert();

        if (alert.getResult() == ButtonType.YES) {
            ObservableList orderList = orderListProductsTableView.getItems();
            Order order = new Order(orderList,
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    emailAddressTextField.getText(),
                    phoneNumberTextField.getText());

            createOrderService.addOrder(order);

            clearInput();
            refreshOrderListProductsTableView();
            showMessage(orderCreatedSuccessfullyMessage);
        } else {
            alert.close();
        }
    }

    private static Alert getAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to confirm this order?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert;
    }

    private void clearInput() {
        createOrderService.deleteAllOrderListProducts();
        firstNameTextField.clear();
        lastNameTextField.clear();
        emailAddressTextField.clear();
        phoneNumberTextField.clear();
    }

    private boolean isValidInput() {
        if (orderListProductsTableView.getItems().isEmpty()) {
            showMessage(noProductAddedErrorMessage);
        } else if (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() ||
                emailAddressTextField.getText().isEmpty() || phoneNumberTextField.getText().isEmpty()) {
            showMessage(notAllFieldsFilledErrorMessage);
        } else if (!emailAddressTextField.getText().contains("@") || !emailAddressTextField.getText().contains(".")) {
            showMessage(emailInputNotValidErrorMessage);
        } else if (!phoneNumberTextField.getText().matches("[0-9]+") || phoneNumberTextField.getText().length() != 10 ||
                !phoneNumberTextField.getText().startsWith("06")) {
            showMessage(phoneNumberNotValidErrorMessage);
        } else {
            return true;
        }
        return false;
    }

    // open add-product-order-view.fxml window
    private void openWindow() throws IOException {
        Scene scene = getSceneLoaded();

        Stage stage = new Stage();
        stage.setTitle("Add product to the order");
        stage.setScene(scene);
        stage.show();
    }

    // load add-product-order-view.fxml
    private Scene getSceneLoaded() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("add-product-order-view.fxml"));
        int appWidth = 800;
        int appHeight = 400;
        Scene scene = new Scene(fxmlLoader.load(), appWidth, appHeight);

        AddProductOrderController addProductOrderController = fxmlLoader.getController();
        addProductOrderController.initialize(this);

        return scene;
    }

    private void showTableViewData() {
        createOrderService.showTableViewData(orderListProductsTableView,
                quantityTableColumn,
                nameTableColumn,
                categoryTableColumn,
                priceTableColumn);
    }

    public void refreshOrderListProductsTableView() {
        showTableViewData();
        orderListProductsTableView.refresh();
    }

    private void showMessage(Label label) {
        List<Label> labels = List.of(productNotSelectedErrorMessage,
                noProductToDeleteErrorMessage,
                notAllFieldsFilledErrorMessage,
                emailInputNotValidErrorMessage,
                phoneNumberNotValidErrorMessage,
                orderCreatedSuccessfullyMessage,
                noProductAddedErrorMessage);

        for (Label l : labels) {
            l.setVisible(l == label);
        }
    }

}