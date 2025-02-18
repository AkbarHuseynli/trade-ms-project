package org.example.msorder.client;

import lombok.Getter;

@Getter
public enum JsonNodeFieldName {
    MESSAGE("message");

    private final String message;

    JsonNodeFieldName(String message) {
        this.message = message;
    }
}
