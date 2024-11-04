package com.akart.user.service;

import com.akart.user.exception.UserServiceResponseException;
import com.akart.user.helper.HelperRestTemplate;
import com.akart.user.model.OrderDto;
import com.akart.user.model.ProductDto;
import com.akart.user.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private HelperRestTemplate helperRestTemplate;

    private final ObjectMapper objectMapper =new ObjectMapper();

    private void validateOrderDto(OrderDto orderDto){
        log.info("Validating Order");
        if (isBlank(orderDto.getProductId())) {
            throw UserServiceResponseException.badRequest().message("ProductId is required");
        }
        if (isBlank(orderDto.getUserName())) {
            throw UserServiceResponseException.badRequest().message("User Name is required");
        }
        if (isBlank(orderDto.getUserId())) {
            throw UserServiceResponseException.badRequest().message("User Id is required");
        }
        if (orderDto.getQuantity()<1) {
            throw UserServiceResponseException.badRequest().message("quantity cannot be 0");
        }

    }


    public OrderDto getOrderById(Long orderId) {
        try {
            Object response = helperRestTemplate.getOderById(orderId);
            return objectMapper.convertValue(response, OrderDto.class);
        } catch (Exception e) {
            throw UserServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public List<OrderDto> getAllOrderByUserId(Long userId) {
        try {
            Object response = helperRestTemplate.getAllOrderByUserId(userId);
            return objectMapper.convertValue(response, new TypeReference<List<OrderDto>>() {});
        } catch (Exception e) {
            throw UserServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public OrderDto createOrder(OrderDto OrderDto) {
        validateOrderDto(OrderDto);
        try {
            Object response = helperRestTemplate.createOrder(OrderDto);
            return objectMapper.convertValue(response, OrderDto.class);
        } catch (Exception e) {
            throw UserServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public OrderDto updateOrder(OrderDto OrderDto) {
        if(OrderDto.getId()<0){
            throw UserServiceResponseException.badRequest().message("Order Id is required");
        }
        validateOrderDto(OrderDto);
        try {
            Object response = helperRestTemplate.updateOrder(OrderDto);
            return objectMapper.convertValue(response, OrderDto.class);
        } catch (Exception e) {
            throw UserServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public boolean deleteOrderById(Long orderId) {
        try {
            helperRestTemplate.deleteOderById(orderId);
            return true;
        } catch (Exception e) {
            throw UserServiceResponseException.internalServerError().message(e.getMessage());
        }
    }
}
