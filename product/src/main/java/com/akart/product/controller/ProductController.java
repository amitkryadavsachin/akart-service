package com.akart.product.controller;

import com.akart.product.model.Product;
import com.akart.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService ProductService;


    @GetMapping()
    public ResponseEntity<Object> getAllProducts() {
        return ResponseEntity.ok(ProductService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(ProductService.getProductById(id).orElse(null));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(ProductService.createProduct(product));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        return ResponseEntity.ok(ProductService.updateProduct(id,updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id) {
        return ResponseEntity.ok(ProductService.deleteProductById(id));
    }
}
