package com.ShoppingCart.java;

import java.util.ArrayList;
import java.util.List;

import com.Product.java.Product;

public class ShoppingCart {
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
}
