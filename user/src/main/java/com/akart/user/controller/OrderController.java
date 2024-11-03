package com.akart.user.controller;


import com.akart.user.model.OrderDto;
import com.akart.user.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/by-user")
    public ResponseEntity<Object>  getAllOrderByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(orderService.getAllOrderByUserId(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.updateOrder(orderDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deleteOrderById(id));
    }

}
