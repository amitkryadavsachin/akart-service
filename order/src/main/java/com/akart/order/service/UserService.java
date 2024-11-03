package com.akart.order.service;

import com.akart.order.exception.OrderServiceResponseException;
import com.akart.order.helper.HelperRestTemplate;
import com.akart.order.model.ProductDto;
import com.akart.order.model.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UserService {

    @Autowired
    private HelperRestTemplate helperRestTemplate;

    private ObjectMapper objectMapper;

    public UserDto geUserById(Long id) {
        try {
            Object response = helperRestTemplate.getUserById(id);
            return objectMapper.convertValue(response, UserDto.class);
        } catch (Exception e) {
            throw OrderServiceResponseException.internalServerError().message(e.getMessage());
        }
    }


}
