package org.example.apigateway.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder
public record AuthResponse(
        String userId,
        String accessToken,
        String refreshToken,
        LocalDateTime expiresAt,
        Collection<String> authorities

) {
}
