package com.akart.product.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProductServiceResponseException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public ProductServiceResponseException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    private ProductServiceResponseException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public static ProductServiceResponseException badRequest() {
        return new ProductServiceResponseException(HttpStatus.BAD_REQUEST);
    }

    public static ProductServiceResponseException internalServerError() {
        return new ProductServiceResponseException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ProductServiceResponseException message(String message) {
        this.message = message;
        return this;
    }
}
