package org.example.mspayment.model.dto;

import lombok.Builder;
import org.example.mspayment.model.enums.PaymentType;

import java.math.BigDecimal;

@Builder
public record PaymentRequest(
        Long orderId,

        PaymentType paymentType,

        BigDecimal amount


) {
}
