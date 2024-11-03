package com.akart.order.service;

import com.akart.order.exception.OrderServiceResponseException;
import com.akart.order.helper.HelperRestTemplate;
import com.akart.order.model.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private HelperRestTemplate helperRestTemplate;

    private ObjectMapper objectMapper;


    public ProductDto geProductById(String id) {
        try {
            Object response = helperRestTemplate.getProductById(id);
            return objectMapper.convertValue(response, ProductDto.class);
        } catch (Exception e) {
            throw OrderServiceResponseException.internalServerError().message(e.getMessage());
        }
    }
}
