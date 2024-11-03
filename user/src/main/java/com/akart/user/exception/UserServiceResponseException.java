package com.akart.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserServiceResponseException extends RuntimeException {
        private HttpStatus httpStatus;
        private String message;

	public UserServiceResponseException(HttpStatus httpStatus, String message) {
            this.httpStatus = httpStatus;
            this.message = message;
        }

	private UserServiceResponseException(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
        }

        public static UserServiceResponseException badRequest() {
            return new UserServiceResponseException(HttpStatus.BAD_REQUEST);
        }

        public static UserServiceResponseException internalServerError() {
            return new UserServiceResponseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        public UserServiceResponseException message(String message) {
            this.message = message;
            return this;
        }
}
