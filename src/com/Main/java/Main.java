package com.Main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    private List<Product> cartItems;

    public ShoppingCart() {
        setCartItems(new ArrayList<>());
    }

    public void addProductToCart(Product product) {
        getCartItems().add(product);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : getCartItems()) {
            total += product.getPrice();
        }
        return total;
    }

    public void clearCart() {
        getCartItems().clear();
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Product> cartItems) {
        this.cartItems = cartItems;
    }
}

public class Main {
    // Helper method to display available products
    private static void displayAvailableProducts(Product product1, Product product2, Product product3) {
        System.out.println("Available Products:");
        System.out.println("1. " + product1.getName() + " - $" + product1.getPrice());
        System.out.println("2. " + product2.getName() + " - $" + product2.getPrice());
        System.out.println("3. " + product3.getName() + " - $" + product3.getPrice());
    }

    public static void main(String[] args) {
        // Sample products
        Product product1 = new Product("Product 1", 10.0);
        Product product2 = new Product("Product 2", 20.0);
        Product product3 = new Product("Product 3", 30.0);

        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OSS", "root", "TIGER")) {
            // Admin and User login credentials
            String adminUsername = "admin";
            String adminPassword = "admin123";

            String userUsername = "user";
            String userPassword = "user123";

            boolean loggedIn = false;
            while (!loggedIn) {
                System.out.println("Login Page:");
                System.out.println("1. Admin Login");
                System.out.println("2. User Login");
                System.out.print("Enter your choice: ");
                int loginChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character after reading int

                System.out.print("Enter your username: ");
                String username = scanner.nextLine();

                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                switch (loginChoice) {
                    case 1:
                        if (username.equals(adminUsername) && password.equals(adminPassword)) {
                            System.out.println("Admin login successful! Welcome to the Online Store!");
                            loggedIn = true;
                        } else {
                            System.out.println("Invalid admin credentials. Please try again.");
                        }
                        break;
                    case 2:
                        if (username.equals(userUsername) && password.equals(userPassword)) {
                            System.out.println("User login successful! Welcome to the Online Store!");
                            loggedIn = true;
                        } else {
                            System.out.println("Invalid user credentials. Please try again.");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1 for Admin or 2 for User login.");
                }
            }

            // Main menu
            boolean shopping = true;
            while (shopping) {
                System.out.println("Main Menu:");
                System.out.println("1. View Products");
                System.out.println("2. Add Product to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Checkout");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character after reading int

                switch (choice) {
                    case 1:
                        displayAvailableProducts(product1, product2, product3);
                        break;
                    case 2:
                        displayAvailableProducts(product1, product2, product3);
                        System.out.print("Enter the product number to add to cart (or 0 to cancel): ");
                        int productNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character after reading int

                        if (productNumber >= 1 && productNumber <= 3) {
                            // Valid product number, add product to cart
                            Product selectedProduct;
                            switch (productNumber) {
                                case 1:
                                    selectedProduct = product1;
                                    break;
                                case 2:
                                    selectedProduct = product2;
                                    break;
                                case 3:
                                    selectedProduct = product3;
                                    break;
                                default:
                                    selectedProduct = null;
                                    break;
                            }

                            if (selectedProduct != null) {
                                cart.addProductToCart(selectedProduct);
                                System.out.println(selectedProduct.getName() + " added to cart.");
                            } else {
                                System.out.println("Invalid product number.");
                            }
                        } else if (productNumber == 0) {
                            System.out.println("Product addition canceled.");
                        } else {
                            System.out.println("Invalid product number.");
                        }
                        break;
                    case 3:
                        System.out.println("Shopping Cart:");
                        for (Product product : cart.getCartItems()) {
                            System.out.println(product.getName() + " - $" + product.getPrice());
                        }
                        break;
                    case 4:
                        System.out.println("Total price: $" + cart.getTotalPrice());
                        cart.clearCart();
                        System.out.println("Thank you for shopping with us!");
                        break;
                    case 5:
                        shopping = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            // Handle any database-related exceptions here
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
