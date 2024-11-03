package com.akart.user.helper;
import com.akart.user.controller.OrderController;
import com.akart.user.controller.ProductController;
import com.akart.user.controller.UserController;
import com.akart.user.exception.UserServiceResponseException;
import com.akart.user.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = { UserController.class, OrderController.class , ProductController.class})
public class ResponseCodeHandler {
    
    @ExceptionHandler(value = { Exception.class, RuntimeException.class })
    public ResponseEntity<ErrorResponse> interExceptionHandler(Exception ex) {
        ErrorResponse ErrorResponse = new ErrorResponse();
        ErrorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse);
    }

    @ExceptionHandler(value = { MissingServletRequestParameterException.class })
    public ResponseEntity<ErrorResponse> handleMissingParameterException(MissingServletRequestParameterException ex) {
        ErrorResponse ErrorResponse = new ErrorResponse();
        ErrorResponse.setMessage(String.format("Missing required parameter : [%s]", ex.getParameterName()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse);
    }

    @ExceptionHandler(value = { UserServiceResponseException.class })
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest req, HttpServletResponse res,
                                                         UserServiceResponseException exception) {
        ErrorResponse ErrorResponse = new ErrorResponse();
        ErrorResponse.setMessage(exception.getMessage());
        return switch (exception.getHttpStatus()) {
            case BAD_REQUEST -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse);
            case UNAUTHORIZED -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse);
            case NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse);
            case FORBIDDEN -> ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse);
            case CONFLICT -> ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse);
            case METHOD_NOT_ALLOWED -> ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ErrorResponse);
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse);
        };
    }

}
