package org.example.msorder.exception;

import lombok.Getter;

@Getter
public class CustomFeignException extends RuntimeException {
    private final Integer httpStatusCode;

    public CustomFeignException(String message, Integer httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }
}
