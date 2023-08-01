package com.Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String product;
    private double price;

    public Product(String product, double price) {
        this.product = product;
        this.price = price;
    }

    // Getters and setters (if needed)

    @Override
    public String toString() {
        return "Product: " + product + ", Price: $" + price;
    }

    // Additional methods for database operations (if needed)
    public String getproduct1() {
        return product;
    }

    public double getPrice() {
        return price;
    }

	public String getproduct() {
		// TODO Auto-generated method stub
		return null;
	}
}

class OnlineShoppingSystem {
    private List<Product> products;
    private boolean isAdminLoggedIn = false;
    private final String adminUsername = "admin";
    private final String adminPassword = "admin123";

    // Database connection parameters
    private String url = "jdbc:mysql://localhost:3306/OSS";
    private String username = "root";
    private String password = "TIGER";

    public OnlineShoppingSystem() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void viewProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public boolean loginAsAdmin(String username, String password) {
        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            setAdminLoggedIn(true);
            System.out.println("Admin logged in successfully.");
            return true;
        } else {
            System.out.println("Invalid admin credentials.");
            return false;
        }
    }

    public void saveProductsToDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OSS", "root", "TIGER")) {
            String query = "INSERT INTO products (product, price) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for (Product product : products) {
                preparedStatement.setString(1, product.getproduct1());
                preparedStatement.setDouble(2, product.getPrice());
                preparedStatement.executeUpdate();
            }

            System.out.println("Products saved to the database.");
        } catch (SQLException e) {
            System.err.println("Error saving products to the database: " + e.getMessage());
        }
    }

    public void viewProductsFromDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OSS","root", "TIGER")) {
            String query = "SELECT product, price FROM products";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Products in the database:");
            while (resultSet.next()) {
                String name = resultSet.getString("Product");
                double price = resultSet.getDouble("price");
                System.out.println("Product: " + products + ", Price: $" + price);
            }
        } catch (SQLException e) {
            System.err.println("Error viewing products from the database: " + e.getMessage());
        }
    }

    public void deleteProductFromDatabase(String product) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OSS 		","root","TIGER")) {
            String query = "DELETE FROM products WHERE product = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product deleted from the database.");
            } else {
                System.out.println("Product not found in the database.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting product from the database: " + e.getMessage());
        }
    }

	public boolean isAdminLoggedIn() {
		return isAdminLoggedIn;
	}

	public void setAdminLoggedIn(boolean isAdminLoggedIn) {
		this.isAdminLoggedIn = isAdminLoggedIn;
	}
}

public class AdminRoleDemo {
    public static void main(String[] args) {
        OnlineShoppingSystem system = new OnlineShoppingSystem(); // Create an instance of OnlineShoppingSystem

        // Admin login
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter admin username: ");
        String adminUsername = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String adminPassword = scanner.nextLine();
        boolean loginSuccessful = system.loginAsAdmin(adminUsername, adminPassword);

        if (loginSuccessful) {
            int choice;
            do {
                System.out.println("\nAdmin tasks menu:");
                System.out.println("1. Add product");
                System.out.println("2. View products");
                System.out.println("3. Delete product");
                System.out.println("0. Logout");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter product price: ");
                        double price = scanner.nextDouble();
                        system.addProduct(new Product(name, price));
                        break;
                    case 2:
                        System.out.println("Products added in this session:");
                        system.viewProducts();
                        System.out.println("Products stored in the database:");
                        system.viewProductsFromDatabase();
                        break;
                    case 3:
                        System.out.print("Enter the product name to delete: ");
                        String productNameToDelete = scanner.nextLine();
                        system.deleteProductFromDatabase(productNameToDelete);
                        break;
                    case 0:
                        system.saveProductsToDatabase();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);
        }

        scanner.close();
    }
}
