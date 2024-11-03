package com.akart.order.controller;

import com.akart.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/by-productId")
    public ResponseEntity<Object> getAllProducts(@RequestParam String productId) {
        return ResponseEntity.ok(productService.geProductById(productId));
    }
}
