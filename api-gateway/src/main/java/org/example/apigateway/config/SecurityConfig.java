package org.example.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http.
//                authorizeExchange((exchange) -> exchange.anyExchange().authenticated())
//                .oauth2Login(Customizer.withDefaults())
//                .oauth2ResourceServer(oAuth2 -> oAuth2.jwt(Customizer.withDefaults()))
//                .build();
//    }

}
