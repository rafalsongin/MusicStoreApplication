package com.example.javafxendassignment.services;

import com.example.javafxendassignment.database.DatabaseAccess;
import com.example.javafxendassignment.model.DatabaseSharedModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProductInventoryService {
    private final DatabaseAccess database;

    public ProductInventoryService() {
        database = DatabaseSharedModel.getDatabase();
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

    public void deleteProduct(String cellData) {
        database.deleteProduct(cellData);
    }
}
