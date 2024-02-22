/*
Author: Shubham Samarth
Date: February 18, 2024
Description: This class defines REST endpoints for customer-specific operations, such as retrieving all products available for purchase.
Last Modified: February 22, 2024
*/

package com.farmbazaar.controller;

import com.farmbazaar.dto.CartItemRequest;
import com.farmbazaar.dto.CheckoutRequest;
import com.farmbazaar.model.entity.CartItem;
import com.farmbazaar.model.entity.Product;
import com.farmbazaar.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return customerService.getAllProducts();
    }

    @PostMapping("/cart/{customerId}/add")
    public ResponseEntity<?> addToCart(@PathVariable int customerId, @RequestBody CartItemRequest cartItemRequest) {
        String response = customerService.addToCart(customerId, cartItemRequest);
        if (response.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cart/{customerId}/items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable int customerId) {
        List<CartItem> cartItems = customerService.getCartItems(customerId);
        if (cartItems.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cartItems);
    }

    @PostMapping("/cart/{customerId}/checkout")
    public ResponseEntity<?> checkoutCart(@PathVariable int customerId, @RequestBody CheckoutRequest checkoutRequest) {
        String response = customerService.checkoutCart(customerId, checkoutRequest);
        if (response.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
