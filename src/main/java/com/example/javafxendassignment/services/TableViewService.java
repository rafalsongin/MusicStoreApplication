package com.example.javafxendassignment.services;

import com.example.javafxendassignment.model.Order;
import com.example.javafxendassignment.model.OrderListProduct;
import com.example.javafxendassignment.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TableViewService {

    public static void showProductsTableData(List<Product> products,
                                             TableView productsTableView,
                                             TableColumn stockTableColumn,
                                             TableColumn nameTableColumn,
                                             TableColumn categoryTableColumn,
                                             TableColumn priceTableColumn,
                                             TableColumn descriptionTableColumn) {
        ObservableList<Product> data = FXCollections.observableArrayList(products);

        productsTableView.setItems(data);

        stockTableColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        categoryTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
    }

    public static void showOrdersTable(List<Order> orders,
                                       TableView orderHistoryTableView,
                                       TableColumn orderDateOfOrderTableColumn,
                                       TableColumn orderNameTableColumn,
                                       TableColumn orderTotalPriceTableColumn) {
        ObservableList<Order> data = FXCollections.observableArrayList(orders);

        orderHistoryTableView.setItems(data);

        orderDateOfOrderTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("dateOfOrder"));
        orderNameTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
        orderTotalPriceTableColumn.setCellValueFactory(new PropertyValueFactory<Order, Double>("totalPrice"));
    }

    public static void showOrderedProductsTable(List<OrderListProduct> orderListProducts,
                                                TableView orderedProductsTableView,
                                                TableColumn productQuantityTableColumn,
                                                TableColumn productNameTableColumn,
                                                TableColumn productCategoryTableColumn,
                                                TableColumn productPriceTableColumn) {
        ObservableList<OrderListProduct> data = FXCollections.observableArrayList(orderListProducts);

        orderedProductsTableView.setItems(data);

        productQuantityTableColumn.setCellValueFactory(new PropertyValueFactory<OrderListProduct, Integer>("quantity"));
        productNameTableColumn.setCellValueFactory(new PropertyValueFactory<OrderListProduct, String>("name"));
        productCategoryTableColumn.setCellValueFactory(new PropertyValueFactory<OrderListProduct, String>("category"));
        productPriceTableColumn.setCellValueFactory(new PropertyValueFactory<OrderListProduct, Double>("fullPrice"));
    }
}
