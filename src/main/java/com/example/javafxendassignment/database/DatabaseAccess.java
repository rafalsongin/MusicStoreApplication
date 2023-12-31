package com.example.javafxendassignment.database;

import com.example.javafxendassignment.model.Order;
import com.example.javafxendassignment.model.OrderListProduct;
import com.example.javafxendassignment.model.Product;
import com.example.javafxendassignment.model.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<User> users;
    private final List<Product> products;
    private final List<Order> orders;
    private final List<OrderListProduct> orderListProducts;

    public DatabaseAccess() {
        this.users = new ArrayList<>();
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.orderListProducts = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(String cellData) {
        for (Product product : products) {
            if (product.getName().equals(cellData)) {
                products.remove(product);
                break;
            }
        }
    }

    public void deleteOrderListProductAndResetStock(String cellData, int quantity) {
        for (OrderListProduct orderListProduct : orderListProducts) {
            if (orderListProduct.getName().equals(cellData)) {
                orderListProducts.remove(orderListProduct);
                break;
            }
        }
        resetProductStock(cellData, quantity);
    }

    public void editProduct(Product productToEdit, Product editedProduct) {
        for (Product product : products) {
            if (product.equals(productToEdit)) {
                product.setName(editedProduct.getName());
                product.setCategory(editedProduct.getCategory());
                product.setPrice(editedProduct.getPrice());
                product.setStock(editedProduct.getStock());
                product.setDescription(editedProduct.getDescription());
                break;
            }
        }
    }

    public List<OrderListProduct> getOrderListProducts() {
        return orderListProducts;
    }

    public void addOrderListProduct(OrderListProduct newOrderListProduct) {
        // if the product is already in the order list, just increase the quantity
        for (OrderListProduct orderListProduct : orderListProducts) {
            if (orderListProduct.getName().equals(newOrderListProduct.getName())) {
                orderListProduct.setQuantity(orderListProduct.getQuantity() + newOrderListProduct.getQuantity());
                return;
            }
        }
        orderListProducts.add(newOrderListProduct);
    }

    public void resetProductStock(String cellData, int quantity) {
        for (Product product : products) {
            if (product.getName().equals(cellData)) {
                product.setStock(product.getStock() + quantity);
                break;
            }
        }
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void deleteAllOrderListProducts() {
        orderListProducts.clear();
    }

    public User getUserByUsername(String inputUsername) {
        for (User user : users) {
            if (user.getUsername().equals(inputUsername)) {
                return user;
            }
        }
        return null;
    }

    public List<Product> filterProducts(String newValue) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(newValue.toLowerCase())) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    public void addProductFromImport(String name, String category, double price, String description, int stock) {
        boolean productExists = false;

        for (Product product : products) {
            if (product.getName().equals(name)) {
                product.setStock(product.getStock() + stock);
                productExists = true;
            }
        }

        if (!productExists) {
            products.add(new Product(stock, name, category, description, price));
        }
    }

}
