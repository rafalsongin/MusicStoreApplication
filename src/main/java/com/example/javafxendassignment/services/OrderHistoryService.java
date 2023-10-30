package com.example.javafxendassignment.services;

import com.example.javafxendassignment.database.DatabaseAccess;
import com.example.javafxendassignment.model.DatabaseSharedModel;
import com.example.javafxendassignment.model.Order;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class OrderHistoryService {

    public DatabaseAccess database;

    public OrderHistoryService() {
        database = DatabaseSharedModel.getDatabase();
    }

    public List<Order> getOrders() {
        return database.getOrders();
    }

    public void showOrdersTable(List<Order> orders,
                                TableView orderHistoryTableView,
                                TableColumn orderDateTimeTableColumn,
                                TableColumn orderNameTableColumn,
                                TableColumn orderTotalPriceTableColumn) {
        TableViewService.showOrdersTable(orders,
                orderHistoryTableView,
                orderDateTimeTableColumn,
                orderNameTableColumn,
                orderTotalPriceTableColumn);
    }
}
