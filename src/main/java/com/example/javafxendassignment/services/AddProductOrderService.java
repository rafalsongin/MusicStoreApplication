package com.example.javafxendassignment.services;

import com.example.javafxendassignment.database.DatabaseAccess;
import com.example.javafxendassignment.model.DatabaseSharedModel;
import com.example.javafxendassignment.model.OrderListProduct;
import com.example.javafxendassignment.model.Product;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class AddProductOrderService {

    private final DatabaseAccess database;

    public AddProductOrderService() {
        database = DatabaseSharedModel.getDatabase();
    }

    public void addOrderListProduct(OrderListProduct orderListProduct) {
        database.addOrderListProduct(orderListProduct);
    }

    public void showTableViewData(TableView productsTableView,
                                  TableColumn stockTableColumn,
                                  TableColumn nameTableColumn,
                                  TableColumn categoryTableColumn,
                                  TableColumn priceTableColumn,
                                  TableColumn descriptionTableColumn) {
        TableViewService.showProductsTableData(database.getProducts(),
                productsTableView,
                stockTableColumn,
                nameTableColumn,
                categoryTableColumn,
                priceTableColumn,
                descriptionTableColumn);
    }

    public List<Product> filterProducts(String newValue) {
        return database.filterProducts(newValue);
    }

    public void showSearchedTableViewData(List<Product> searchedProducts, TableView productsTableView, TableColumn stockTableColumn, TableColumn nameTableColumn, TableColumn categoryTableColumn, TableColumn priceTableColumn, TableColumn descriptionTableColumn) {
        TableViewService.showProductsTableData(searchedProducts,
                productsTableView,
                stockTableColumn,
                nameTableColumn,
                categoryTableColumn,
                priceTableColumn,
                descriptionTableColumn);
    }
}
