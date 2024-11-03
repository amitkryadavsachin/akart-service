package com.akart.order.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product_account")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private Long userId;
    private String userName;
    private int quantity;
}
