package org.example.msorder.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.example.msorder.model.enums.Constants;
import org.example.msorder.model.enums.PaymentType;

@Builder
public record OrderRequest(
        @NotNull(message = Constants.PRODUCT_ID_REQUIRED)
        Long productId,

        @NotNull(message = Constants.QUANTITY_REQUIRED)
        Integer quantity,

        @NotNull
        PaymentType paymentType) {
}
