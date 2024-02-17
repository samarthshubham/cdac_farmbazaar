/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This class defines REST endpoints for performing CRUD operations related to various user roles (Admin, Farmer, Delivery Partner, Customer), categories, products, and user creation. It also includes methods for assigning products to farmers and retrieving user details by ID for all user types.
*/

package com.farmbazaar.controller;

import com.farmbazaar.dto.UserDetailsDTO;
import com.farmbazaar.dto.UserRequestDTO;
import com.farmbazaar.enums.Role;
import com.farmbazaar.model.entity.*;
import com.farmbazaar.model.repository.*;
import com.farmbazaar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductService productService;

    // CRUD operations for Admin users

    @PostMapping("/admin-users")
    public Admin createAdminUser(@RequestBody Admin user) {
    	user.setRole(Role.ADMIN);
        return adminRepository.save(user);
    }

    @PutMapping("/admin-users/{id}")
    public Admin updateAdminUser(@PathVariable int id, @RequestBody Admin userDetails) {
        Admin user = adminRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setFname(userDetails.getFname());
            user.setLname(userDetails.getLname());
            user.setPhno(userDetails.getPhno());
            user.setAddress(userDetails.getAddress());
            user.setRole(userDetails.getRole());
            user.setActive(userDetails.isActive());
            
            // Update other user details as needed
            
            return adminRepository.save(user);
        }
        return null; // Handle not found
    }


    @DeleteMapping("/admin-users/{id}")
    public void deleteAdminUser(@PathVariable int id) {
        adminRepository.deleteById(id);
    }

    @GetMapping("/admin-users")
    public List<Admin> getAllAdminUsers() {
        return adminRepository.findAll();
    }

    // CRUD operations for Farmer users

    @PostMapping("/farmer-users")
    public Farmer createFarmerUser(@RequestBody Farmer user) {
    	user.setRole(Role.FARMER);
        return farmerRepository.save(user);
    }

    @PutMapping("/farmer-users/{id}")
    public Farmer updateFarmerUser(@PathVariable int id, @RequestBody Farmer userDetails) {
        Farmer user = farmerRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setFname(userDetails.getFname());
            user.setLname(userDetails.getLname());
            user.setPhno(userDetails.getPhno());
            user.setAddress(userDetails.getAddress());
            user.setRole(userDetails.getRole());
            user.setActive(userDetails.isActive());
            
            // Update other user details as needed
            
            return farmerRepository.save(user);
        }
        return null; // Handle not found
    }

    @DeleteMapping("/farmer-users/{id}")
    public void deleteFarmerUser(@PathVariable int id) {
        farmerRepository.deleteById(id);
    }

    @GetMapping("/farmer-users")
    public List<Farmer> getAllFarmerUsers() {
        return farmerRepository.findAll();
    }

    // CRUD operations for Delivery Partner users

    @PostMapping("/delivery-partner-users")
    public DeliveryPartner createDeliveryPartnerUser(@RequestBody DeliveryPartner user) {
    	user.setRole(Role.DELIVERY_PARTNER);
        return deliveryPartnerRepository.save(user);
    }

    @PutMapping("/delivery-partner-users/{id}")
    public DeliveryPartner updateDeliveryPartnerUser(@PathVariable int id, @RequestBody DeliveryPartner userDetails) {
        DeliveryPartner user = deliveryPartnerRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setFname(userDetails.getFname());
            user.setLname(userDetails.getLname());
            user.setPhno(userDetails.getPhno());
            user.setAddress(userDetails.getAddress());
            user.setRole(userDetails.getRole());
            user.setActive(userDetails.isActive());
            
            // Update other user details as needed
            
            return deliveryPartnerRepository.save(user);
        }
        return null; // Handle not found
    }


    @DeleteMapping("/delivery-partner-users/{id}")
    public void deleteDeliveryPartnerUser(@PathVariable int id) {
        deliveryPartnerRepository.deleteById(id);
    }

    @GetMapping("/delivery-partner-users")
    public List<DeliveryPartner> getAllDeliveryPartnerUsers() {
        return deliveryPartnerRepository.findAll();
    }

    // CRUD operations for Customer users

    @PostMapping("/customer-users")
    public Customer createCustomerUser(@RequestBody Customer user) {
    	user.setRole(Role.CUSTOMER);
        return customerRepository.save(user);
    }

    @PutMapping("/customer-users/{id}")
    public Customer updateCustomerUser(@PathVariable int id, @RequestBody Customer userDetails) {
        Customer user = customerRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setFname(userDetails.getFname());
            user.setLname(userDetails.getLname());
            user.setPhno(userDetails.getPhno());
            user.setAddress(userDetails.getAddress());
            user.setRole(userDetails.getRole());
            user.setActive(userDetails.isActive());
            
            // Update other user details as needed
            
            return customerRepository.save(user);
        }
        return null; // Handle not found
    }


    @DeleteMapping("/customer-users/{id}")
    public void deleteCustomerUser(@PathVariable int id) {
        customerRepository.deleteById(id);
    }

    @GetMapping("/customer-users")
    public List<Customer> getAllCustomerUsers() {
        return customerRepository.findAll();
    }

    // CRUD operations for categories

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category categoryDetails) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setName(categoryDetails.getName());
            // Update other category details as needed
            return categoryRepository.save(category);
        }
        return null; // Handle not found
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryRepository.deleteById(id);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // CRUD operations for products

    @PostMapping("/products")
    public Product createProduct(@RequestParam("name") String name,
                                 @RequestParam("price") double price,
                                 @RequestParam("quantity") double quantity,
                                 @RequestParam("pre_order_quantity") double preOrderQuantity,
                                 @RequestParam("category_id") int categoryId,
                                 @RequestParam("imageFile") MultipartFile imageFile) {
        return productService.createProduct(name, price, quantity, preOrderQuantity, categoryId, imageFile);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable int id, @RequestParam("imageFile") MultipartFile imageFile, @RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("quantity") double quantity, @RequestParam("pre_order_quantity") double preOrderQuantity) {
        try {
            Product product = productRepository.findById(id).orElse(null);
            if (product != null) {
                // Update image data if provided
                if (!imageFile.isEmpty()) {
                    byte[] imageData = imageFile.getBytes();
                    product.setImage(imageData);
                }
                // Update other product details
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setPre_order_quantity(preOrderQuantity);
                // Save the updated product to the database
                return productRepository.save(product);
            }
            return null; // Handle not found
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
            return null;
        }
    }


    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    
    // Assign product to farmer

    @PostMapping("/assign/{farmerId}")
    public void assignProductsToFarmer(@PathVariable Integer farmerId, @RequestBody List<Integer> productIds) {
        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        if (farmer != null) {
            List<Product> products = productRepository.findAllById(productIds);
            products.forEach(product -> farmer.getProducts().add(product));
            farmerRepository.save(farmer);
        }
    }

    // Endpoint to get products assigned to a specific farmer
    @GetMapping("/{farmerId}/products")
    public List<Product> getProductsByFarmerId(@PathVariable int farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        if (farmer != null) {
            return farmer.getProducts();
        } else {
            // Handle case where farmer with given ID is not found
            return null; // Or return an empty list or throw an exception as per your requirement
        }
    }

    // creating user based on role (signup)
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            // Convert DTO to AbstractUser object
            AbstractUser user = userRequestDTO.toUser(); 

            // Handle user creation based on role
            switch (user.getRole()) {
                case ADMIN:
                    // Create an instance of Admin
                    Admin admin = new Admin();
                    // Set common fields
                    admin.setUsername(user.getUsername());
                    admin.setPassword(user.getPassword());
                    admin.setFname(user.getFname());
                    admin.setLname(user.getLname());
                    admin.setPhno(user.getPhno());
                    admin.setAddress(user.getAddress());
                    admin.setRole(user.getRole());
                    // Save Admin entity
                    adminRepository.save(admin);
                    return ResponseEntity.ok(admin);
                case FARMER:
                    // Create an instance of Farmer
                    Farmer farmer = new Farmer();
                    // Set common fields
                    farmer.setUsername(user.getUsername());
                    farmer.setPassword(user.getPassword());
                    farmer.setFname(user.getFname());
                    farmer.setLname(user.getLname());
                    farmer.setPhno(user.getPhno());
                    farmer.setAddress(user.getAddress());
                    farmer.setRole(user.getRole());
                    // Save Farmer entity
                    farmerRepository.save(farmer);
                    return ResponseEntity.ok(farmer);
                case DELIVERY_PARTNER:
                    // Create an instance of DeliveryPartner
                    DeliveryPartner deliveryPartner = new DeliveryPartner();
                    // Set common fields
                    deliveryPartner.setUsername(user.getUsername());
                    deliveryPartner.setPassword(user.getPassword());
                    deliveryPartner.setFname(user.getFname());
                    deliveryPartner.setLname(user.getLname());
                    deliveryPartner.setPhno(user.getPhno());
                    deliveryPartner.setAddress(user.getAddress());
                    deliveryPartner.setRole(user.getRole());
                    // Save DeliveryPartner entity
                    deliveryPartnerRepository.save(deliveryPartner);
                    return ResponseEntity.ok(deliveryPartner);
                case CUSTOMER:
                    // Create an instance of Customer
                    Customer customer = new Customer();
                    // Set common fields
                    customer.setUsername(user.getUsername());
                    customer.setPassword(user.getPassword());
                    customer.setFname(user.getFname());
                    customer.setLname(user.getLname());
                    customer.setPhno(user.getPhno());
                    customer.setAddress(user.getAddress());
                    customer.setRole(user.getRole());
                    // Save Customer entity
                    customerRepository.save(customer);
                    return ResponseEntity.ok(customer);
                default:
                    return ResponseEntity.badRequest().body("Invalid role. Please select a valid role.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user.");
        }
    }

    // Method to get user details by ID for all user types
    @GetMapping("/users/{id}")
    public ResponseEntity<List<UserDetailsDTO>> getUserById(@PathVariable int id) {
        List<UserDetailsDTO> userDetailsList = new ArrayList<>();

        adminRepository.findById(id).ifPresent(admin -> {
            UserDetailsDTO userDetails = new UserDetailsDTO();
            userDetails.setId(admin.getId());
            userDetails.setUsername(admin.getUsername());
            userDetails.setRole(Role.ADMIN.toString());
            userDetailsList.add(userDetails);
        });

        farmerRepository.findById(id).ifPresent(farmer -> {
            UserDetailsDTO userDetails = new UserDetailsDTO();
            userDetails.setId(farmer.getId());
            userDetails.setUsername(farmer.getUsername());
            userDetails.setRole(Role.FARMER.toString());
            userDetailsList.add(userDetails);
        });

        deliveryPartnerRepository.findById(id).ifPresent(deliveryPartner -> {
            UserDetailsDTO userDetails = new UserDetailsDTO();
            userDetails.setId(deliveryPartner.getId());
            userDetails.setUsername(deliveryPartner.getUsername());
            userDetails.setRole(Role.DELIVERY_PARTNER.toString());
            userDetailsList.add(userDetails);
        });

        customerRepository.findById(id).ifPresent(customer -> {
            UserDetailsDTO userDetails = new UserDetailsDTO();
            userDetails.setId(customer.getId());
            userDetails.setUsername(customer.getUsername());
            userDetails.setRole(Role.CUSTOMER.toString());
            userDetailsList.add(userDetails);
        });

        if (userDetailsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userDetailsList);
        }
    }
}
