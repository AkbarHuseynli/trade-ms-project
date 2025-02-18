package org.example.products.model.enums;


public enum ErrorMessage {
    PRODUCT_NOT_FOUND("Product not found by id: %s"),
    INSUFFICIENT_STOCK("The stock has been run out, product id: %s"),
    SEVER_ERROR("Unexpected error occurred!");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
