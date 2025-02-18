package org.example.products.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ErrorResponse(String timestamp,
                            String message
) {
}
