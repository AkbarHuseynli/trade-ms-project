package org.example.msorder.model.enums;


public enum ErrorMessage {

    ORDER_NOT_FOUND("Order not found by id: %s"),
    CLIENT_ERROR("Client error occurred while making the request"),
    SERVER_ERROR("Server error occurred while making the request");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
