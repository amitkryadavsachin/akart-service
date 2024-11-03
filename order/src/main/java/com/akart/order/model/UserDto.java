package com.akart.order.model;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String contactNo;
    private String address;
    private String city;
    private String state;
    private String pinCode;
}
