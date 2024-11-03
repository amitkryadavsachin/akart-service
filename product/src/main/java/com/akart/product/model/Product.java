package com.akart.product.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product_account")
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
}
