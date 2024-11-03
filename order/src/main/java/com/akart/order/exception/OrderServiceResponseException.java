package com.akart.order.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OrderServiceResponseException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public OrderServiceResponseException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    private OrderServiceResponseException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public static OrderServiceResponseException badRequest() {
        return new OrderServiceResponseException(HttpStatus.BAD_REQUEST);
    }

    public static OrderServiceResponseException internalServerError() {
        return new OrderServiceResponseException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public OrderServiceResponseException message(String message) {
        this.message = message;
        return this;
    }

}
