package org.example.products.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Builder;
import java.math.BigDecimal;

import static org.example.products.model.enums.Constants.*;

@Builder
public record ProductRequest(
        @NotBlank(message = name_required)
        String name,

        @NotBlank(message = desc_required)
        String description,

        @DecimalMin(value = "0.0", inclusive = false, message = price_required)
        BigDecimal price,

        @Min(value = 1, message = stock_required)
        Integer stock
) {
}
