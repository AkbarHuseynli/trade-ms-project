package org.example.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(ProductNotFoundException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(e.getMessage())
                .build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(MethodArgumentNotValidException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(e.getBindingResult().getFieldError().getDefaultMessage())
                .build();

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(InsufficientStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(InsufficientStockException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(e.getMessage())
                .build();
    }
}
