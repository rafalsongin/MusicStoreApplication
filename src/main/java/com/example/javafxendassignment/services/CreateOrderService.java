package com.example.javafxendassignment.services;

import com.example.javafxendassignment.database.DatabaseAccess;
import com.example.javafxendassignment.model.DatabaseSharedModel;
import com.example.javafxendassignment.model.Order;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CreateOrderService {
    private final DatabaseAccess database;

    public CreateOrderService() {
        database = DatabaseSharedModel.getDatabase();
    }


    public void deleteOrderListProductAndResetStock(String cellData, int quantity) {
        database.deleteOrderListProductAndResetStock(cellData, quantity);
    }

    public void addOrder(Order order) {
        database.addOrder(order);
    }

    public void deleteAllOrderListProducts() {
        database.deleteAllOrderListProducts();
    }

    public void showTableViewData(TableView orderListProductsTableView,
                                  TableColumn quantityTableColumn,
                                  TableColumn nameTableColumn,
                                  TableColumn categoryTableColumn,
                                  TableColumn priceTableColumn) {
        TableViewService.showOrderedProductsTable(database.getOrderListProducts(),
                orderListProductsTableView,
                quantityTableColumn,
                nameTableColumn,
                categoryTableColumn,
                priceTableColumn);
    }
}
