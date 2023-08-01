package com.abstrac;
import java.util.ArrayList;
import java.util.List;

//Abstract class to represent a product
abstract class Product {
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

 // Abstract method to calculate any additional charges
 public abstract double calculateAdditionalCharges();
}

//Concrete class representing a physical product
class PhysicalProduct extends Product {
 private double shippingCost;

 public PhysicalProduct(String name, double price, double shippingCost) {
     super(name, price);
     this.shippingCost = shippingCost;
 }

 // Implementing the abstract method to calculate shipping charges
 @Override
 public double calculateAdditionalCharges() {
     return shippingCost;
 }
}

//Concrete class representing a digital product
class DigitalProduct extends Product {
 private double taxRate;

 public DigitalProduct(String name, double price, double taxRate) {
     super(name, price);
     this.taxRate = taxRate;
 }

 // Implementing the abstract method to calculate tax
 @Override
 public double calculateAdditionalCharges() {
     return getPrice() * taxRate;
 }
}

//Interface to represent a shopping cart
interface ShoppingCart {
 void addProductToCart(Product product);
 double getTotalPrice();
 void clearCart();
 List<Product> getCartItems();
}

//Concrete class implementing the ShoppingCart interface
class OnlineShoppingCart implements ShoppingCart {
 private List<Product> cartItems;

 public OnlineShoppingCart(List<Product> cartItems) {
     this.cartItems = cartItems;
 }

 @Override
 public void addProductToCart(Product product) {
     cartItems.add(product);
 }

 @Override
 public double getTotalPrice() {
     double total = 0;
     for (Product product : cartItems) {
         total += product.getPrice() + product.calculateAdditionalCharges();
     }
     return total;
 }

 @Override
 public void clearCart() {
     cartItems.clear();
 }

 @Override
 public List<Product> getCartItems() {
     return cartItems;
 }
}

public class Main {
 public static void main(String[] args) {
     // Sample products
     Product physicalProduct = new PhysicalProduct("Physical Product", 50.0, 5.0);
     Product digitalProduct = new DigitalProduct("Digital Product", 30.0, 0.1);

     // Shopping cart
     OnlineShoppingCart cart = new OnlineShoppingCart(new ArrayList<>());

     // Add products to the cart
     cart.addProductToCart(physicalProduct);
     cart.addProductToCart(digitalProduct);

     // Display cart items and total price
     List<Product> cartItems = cart.getCartItems();
     for (Product product : cartItems) {
         System.out.println(product.getName() + " - $" + product.getPrice() +
                 " (" + product.calculateAdditionalCharges() + ")");
     }

     double totalPrice = cart.getTotalPrice();
     System.out.println("Total price: $" + totalPrice);
 }
}
