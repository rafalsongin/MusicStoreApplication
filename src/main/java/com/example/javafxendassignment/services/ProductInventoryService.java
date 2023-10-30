package com.example.javafxendassignment.services;

import com.example.javafxendassignment.database.DatabaseAccess;
import com.example.javafxendassignment.model.DatabaseSharedModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.io.File;

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

    public void importProducts() {

        FileService fileService = new FileService();
        File file = chooseCsvFileToImport();

        if (file != null) {
//            fileService.importProducts(file);
            System.out.println("File selected: " + file.getName());

        } else {
            System.out.println("No file selected");
        }


    }

    private File chooseCsvFileToImport() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        fileChooser.setInitialDirectory(new java.io.File("."));

        return fileChooser.showOpenDialog(null);
    }
}
