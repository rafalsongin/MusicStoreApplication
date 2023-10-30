package com.example.javafxendassignment.controllers;

import com.example.javafxendassignment.model.Product;
import com.example.javafxendassignment.services.AddProductInventoryService;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class AddProductInventoryController {
    @FXML
    public Button addToInventoryButton;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField categoryTextField;
    @FXML
    public TextField priceTextField;
    @FXML
    public TextField quantityTextField;
    @FXML
    public TextArea descriptionTextField;
    @FXML
    public Button closeButton;
    @FXML
    public Label productSuccessfullyAddedMessage;
    @FXML
    public Label nameInputMissingErrorMessage;
    @FXML
    public Label categoryInputMissingErrorMessage;
    @FXML
    public Label descriptionInputMissingErrorMessage;
    @FXML
    public Label quantityInputMissingErrorMessage;
    @FXML
    public Label priceInputMissingErrorMessage;
    @FXML
    public Label failedToAddProductErrorMessage;

    private ProductInventoryController productInventoryController;
    private AddProductInventoryService addProductInventoryService;

    public void initialize(ProductInventoryController productInventoryController) {
        addProductInventoryService = new AddProductInventoryService();
        this.productInventoryController = productInventoryController;
    }

    @FXML
    public void clickAddToInventoryButton() {
        try {
            if (nameTextField.getText().isEmpty() || categoryTextField.getText().isEmpty() ||
                    priceTextField.getText().isEmpty() || quantityTextField.getText().isEmpty() ||
                    descriptionTextField.getText().isEmpty() ||
                    !priceTextField.getText().matches("[0-9]+") ||
                    !quantityTextField.getText().matches("[0-9]+")) {
                inputValidation();
                return;
            }

            String name = nameTextField.getText();
            String category = categoryTextField.getText();


            double price = Double.parseDouble(priceTextField.getText());
            int quantity = Integer.parseInt(quantityTextField.getText());
            String description = descriptionTextField.getText();

            Product product = new Product(quantity, name, category, description, price);
            addProductInventoryService.addProductToInventory(product);

            showMessage(productSuccessfullyAddedMessage);
        } catch (Exception e) {
            inputValidation();
        }
    }

    @FXML
    public void clickCancelSelectProductButton(MouseEvent mouseEvent) {
        closeWindow(mouseEvent);
    }

    private void inputValidation() {
        if (nameTextField.getText().isEmpty()) {
            showMessage(nameInputMissingErrorMessage);
        } else if (categoryTextField.getText().isEmpty()) {
            showMessage(categoryInputMissingErrorMessage);
        } else if (priceTextField.getText().isEmpty() || Double.parseDouble(priceTextField.getText()) <= 0 ||
                !priceTextField.getText().matches("[0-9]+")) {
            showMessage(priceInputMissingErrorMessage);
        } else if (quantityTextField.getText().isEmpty() || Integer.parseInt(quantityTextField.getText()) <= 0 ||
                !quantityTextField.getText().matches("[0-9]+")) {
            showMessage(quantityInputMissingErrorMessage);
        } else if (descriptionTextField.getText().isEmpty()) {
            showMessage(descriptionInputMissingErrorMessage);
        }
    }


    private void closeWindow(MouseEvent mouseEvent) {
        productInventoryController.refreshProductsTableView();
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide(); // close current window
    }

    private void showMessage(Label label) {
        List<Label> labels = List.of(productSuccessfullyAddedMessage,
                nameInputMissingErrorMessage,
                categoryInputMissingErrorMessage,
                descriptionInputMissingErrorMessage,
                quantityInputMissingErrorMessage,
                priceInputMissingErrorMessage,
                failedToAddProductErrorMessage);
        for (Label l : labels) {
            l.setVisible(l.equals(label));
        }
    }
}
