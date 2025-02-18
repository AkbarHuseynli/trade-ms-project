package org.example.mspayment.model.enums;


public enum ErrorMessage {

    PAYMENT_NOT_FOUND("Payment not found by id: %s");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
