package com.akart.order.controller;

import com.akart.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService UserService;

    @GetMapping("/by-userId")
    public ResponseEntity<Object> getAllProducts(@RequestParam Long userId) {
        return ResponseEntity.ok(UserService.geUserById(userId));
    }
}
