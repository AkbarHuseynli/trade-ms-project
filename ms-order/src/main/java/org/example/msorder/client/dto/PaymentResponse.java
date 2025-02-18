package org.example.msorder.client.dto;

import lombok.Builder;
import org.example.msorder.model.enums.PaymentType;

import java.math.BigDecimal;

@Builder
public record PaymentResponse(
        Long id,
        PaymentType paymentType,

        BigDecimal amount

) {

}
