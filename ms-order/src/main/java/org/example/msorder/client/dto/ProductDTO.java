package org.example.msorder.client.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDTO(        Long id,
                                 String name,
                                 String description,
                                 BigDecimal price,
                                 Integer stock) {
}
