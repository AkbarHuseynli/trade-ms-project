package org.example.apigateway.config;

import org.example.apigateway.model.AuthResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.time.LocalDateTime;

public enum AuthMapper {

    AUTH_MAPPER;

    public AuthResponse buildAuthResponse(OidcUser oidcUser,
                                          OAuth2AuthorizedClient client){
        return AuthResponse.builder()
                .userId(oidcUser.getEmail())
                .authorities(oidcUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .accessToken(client.getAccessToken().getTokenValue())
                .refreshToken(client.getRefreshToken().getTokenValue())
                .expiresAt(LocalDateTime.from(client.getAccessToken().getExpiresAt()))
                .build();
    }

}
