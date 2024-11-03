package com.akart.order.controller;
import com.akart.order.model.Order;
import com.akart.order.repository.OrderRepository;
import com.akart.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<Object> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object>  getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id).orElse(null));
    }

    @GetMapping("/by-user")
    public ResponseEntity<Object>  getAllOrderByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(orderService.getAllOrderByUserId(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<Object>  createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateOrder( @RequestBody Order updatedOrder) {
        return ResponseEntity.ok(orderService.updateOrder(updatedOrder));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deleteOrderById(id));
    }
}
