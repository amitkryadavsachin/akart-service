package com.akart.order.model;

import lombok.Data;

@Data
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private double price;
}
