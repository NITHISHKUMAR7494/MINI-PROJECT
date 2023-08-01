package com.encap;
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
        cartItems = new ArrayList<>();
    }

    public void addProductToCart(Product product) {
        cartItems.add(product);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
    }

    public List<Product> getCartItems() {
        return new ArrayList<>(cartItems); // Return a copy to prevent direct manipulation
    }
}

class OnlineStore {
    private List<Product> availableProducts;
    private ShoppingCart cart;

    public OnlineStore() {
        availableProducts = new ArrayList<>();
        cart = new ShoppingCart();
    }

    public void addProduct(Product product) {
        availableProducts.add(product);
    }

    public List<Product> getAvailableProducts() {
        return new ArrayList<>(availableProducts); // Return a copy to prevent direct manipulation
    }

    public void addToCart(int productNumber) {
        if (productNumber >= 1 && productNumber <= availableProducts.size()) {
            Product selectedProduct = availableProducts.get(productNumber - 1);
            cart.addProductToCart(selectedProduct);
            System.out.println(selectedProduct.getName() + " added to cart.");
        } else {
            System.out.println("Invalid product number.");
        }
    }

    public void viewCart() {
        List<Product> cartItems = cart.getCartItems();
        if (cartItems.isEmpty()) {
            System.out.println("Shopping Cart is empty.");
        } else {
            System.out.println("Shopping Cart:");
            for (Product product : cartItems) {
                System.out.println(product.getName() + " - $" + product.getPrice());
            }
        }
    }

    public <returMainn> double checkout() {
        cart.clearCart();
        System.out.println("Thank you for shopping with us!");
        returMainn total1;
		return 0;
    }
}

public class product {
    // Helper method to display available products
    private static void displayAvailableProducts(List<Product> products) {
        System.out.println("Available Products:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice());
        }
    }

    public static void main(String[] args) {
        // Sample products
        Product product1 = new Product("Product 1", 10.0);
        Product product2 = new Product("Product 2", 20.0);
        Product product3 = new Product("Product 3", 30.0);

        OnlineStore store = new OnlineStore();
        store.addProduct(product1);
        store.addProduct(product2);
        store.addProduct(product3);

        Scanner scanner = new Scanner(System.in);

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
                    List<Product> availableProducts = store.getAvailableProducts();
                    displayAvailableProducts(availableProducts);
                    break;
                case 2:
                    List<Product> products = store.getAvailableProducts();
                    displayAvailableProducts(products);
                    System.out.print("Enter the product number to add to cart (or 0 to cancel): ");
                    int productNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading int
                    store.addToCart(productNumber);
                    break;
                case 3:
                    store.viewCart();
                    break;
                case 4:
                    double totalPrice = store.checkout();
                    System.out.println("Total price: $" + totalPrice);
                    break;
                case 5:
                    shopping = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
