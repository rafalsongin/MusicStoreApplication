package com.example.javafxendassignment.controllers;

import com.example.javafxendassignment.model.Order;
import com.example.javafxendassignment.model.OrderListProduct;
import com.example.javafxendassignment.services.OrderHistoryService;
import com.example.javafxendassignment.services.TableViewService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class OrderHistoryController {
    @FXML
    public TableView orderHistoryTableView;
    @FXML
    public TableColumn orderDateTimeTableColumn;
    @FXML
    public TableColumn orderNameTableColumn;
    @FXML
    public TableColumn orderTotalPriceTableColumn;
    @FXML
    public TableView orderedProductsTableView;
    @FXML
    public TableColumn productQuantityTableColumn;
    @FXML
    public TableColumn productNameTableColumn;
    @FXML
    public TableColumn productCategoryTableColumn;
    @FXML
    public TableColumn productPriceTableColumn;

    public void initialize() {
        OrderHistoryService orderHistoryService = new OrderHistoryService();
        List<Order> orders = orderHistoryService.getOrders();

        orderHistoryService.showOrdersTable(orders,
                orderHistoryTableView,
                orderDateTimeTableColumn,
                orderNameTableColumn,
                orderTotalPriceTableColumn);

        orderHistoryTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Order selectedOrder = (Order) orderHistoryTableView.getSelectionModel().getSelectedItem();
                List<OrderListProduct> orderListProducts = selectedOrder.getOrderedListProducts();
                TableViewService.showOrderedProductsTable(orderListProducts,
                        orderedProductsTableView,
                        productQuantityTableColumn,
                        productNameTableColumn,
                        productCategoryTableColumn,
                        productPriceTableColumn);
            }
        });
    }
}