package org.example.msorder.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(String timestamp,
                            String message
                          ) {
}
