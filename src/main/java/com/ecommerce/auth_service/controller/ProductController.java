package com.ecommerce.auth_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.auth_service.entity.Product;
import com.ecommerce.auth_service.repository.ProductRepository;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productRepository.deleteById(id);

        return "Product Deleted";
    }
}