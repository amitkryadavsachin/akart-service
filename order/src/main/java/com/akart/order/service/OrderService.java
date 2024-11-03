package com.akart.order.service;

import com.akart.order.exception.OrderServiceResponseException;
import com.akart.order.model.Order;
import com.akart.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    private void validateOrder(Order order){
        log.info("Validating Order");
        if (isBlank(order.getProductId())) {
            throw OrderServiceResponseException.badRequest().message("ProductId is required");
        }
        if (isBlank(order.getUserName())) {
            throw OrderServiceResponseException.badRequest().message("User Name is required");
        }
        if (order.getUserId()<1) {
            throw OrderServiceResponseException.badRequest().message("User Id is required");
        }
        if (order.getQuantity()<1) {
            throw OrderServiceResponseException.badRequest().message("quantity cannot be 0");
        }

    }

    public List<Order> getAllOrder() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            throw OrderServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public List<Order> getAllOrderByUserId(Long userId) {
        try {
            return orderRepository.findAllOrderByUserId(userId);
        } catch (Exception e) {
            throw OrderServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public Optional<Order> getOrderById(Long id) {
        try {
            return orderRepository.findById(id);
        } catch (Exception e) {
            throw OrderServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public Order createOrder(Order order) {
        validateOrder(order);
        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            throw OrderServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public Order updateOrder(Order updatedOrder) {
        if(updatedOrder.getId()<0){
            throw OrderServiceResponseException.badRequest().message("Order Id is required");
        }
        validateOrder(updatedOrder);
        try {
            Order order = orderRepository.findById(updatedOrder.getId()).orElseThrow();
            order.setUserId(updatedOrder.getUserId());
            order.setUserName(updatedOrder.getUserName());
            order.setProductId(updatedOrder.getProductId());
            order.setQuantity(updatedOrder.getQuantity());
            return orderRepository.save(order);
        } catch (Exception e) {
            throw OrderServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public boolean deleteOrderById(Long id) {
        try {
            orderRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw OrderServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

}
