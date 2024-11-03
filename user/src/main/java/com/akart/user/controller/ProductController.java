package com.akart.user.controller;

import com.akart.user.model.User;
import com.akart.user.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public ResponseEntity<Object> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
