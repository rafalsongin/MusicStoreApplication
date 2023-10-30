package com.example.javafxendassignment.controllers;

import com.example.javafxendassignment.model.Product;
import com.example.javafxendassignment.services.EditProductService;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class EditProductController {
    @FXML
    public Button confirmChangesButton;
    @FXML
    public TextField nameTextField;
    @FXML
    public Button discardChangesButton;
    @FXML
    public TextField categoryTextField;
    @FXML
    public TextField priceTextField;
    @FXML
    public TextField quantityTextField;
    @FXML
    public Label productSuccessfullyEditedMessage;
    @FXML
    public Label failedToAddProductErrorMessage;
    @FXML
    public TextArea descriptionTextField;
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

    private ProductInventoryController productInventoryController;
    private Product productToEdit;

    private EditProductService editProductService;

    public void initialize(ProductInventoryController productInventoryController, Product product) {
        editProductService = new EditProductService();
        productToEdit = product;
        this.productInventoryController = productInventoryController;

        nameTextField.setText(product.getName());
        categoryTextField.setText(product.getCategory());
        priceTextField.setText(String.valueOf(product.getPrice()));
        quantityTextField.setText(String.valueOf(product.getStock()));
        descriptionTextField.setText(product.getDescription());
    }

    @FXML
    public void clickConfirmChangesButton() {

        try {
            String name = nameTextField.getText();
            String category = categoryTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int quantity = Integer.parseInt(quantityTextField.getText());
            String description = descriptionTextField.getText();

            Product editedProduct = new Product(quantity, name, category, description, price);
            editProductService.editProduct(productToEdit, editedProduct);

            showMessage(productSuccessfullyEditedMessage);
            discardChangesButton.setText("Close");
        } catch (Exception e) {
            showMessage(failedToAddProductErrorMessage);
        }
    }

    @FXML
    public void clickDiscardChangesButton(MouseEvent mouseEvent) {
        productInventoryController.refreshProductsTableView();
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide(); // close current window
    }

    private void showMessage(Label label) {
        List<Label> labels = List.of(productSuccessfullyEditedMessage,
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
