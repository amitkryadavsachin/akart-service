package com.akart.user.service;

import com.akart.user.exception.UserServiceResponseException;
import com.akart.user.helper.HelperRestTemplate;
import com.akart.user.model.ProductDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private HelperRestTemplate helperRestTemplate;

    private final ObjectMapper objectMapper =new ObjectMapper();


    public List<ProductDto> getAllProducts() {
        try {
            Object response = helperRestTemplate.getProductList();
            return objectMapper.convertValue(response, new TypeReference<List<ProductDto>>() {});
        } catch (Exception e) {
            throw UserServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

}
