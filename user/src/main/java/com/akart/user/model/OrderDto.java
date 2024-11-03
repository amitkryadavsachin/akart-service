package com.akart.user.model;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private String productId;
    private String userId;
    private String userName;
    private int quantity;
}
