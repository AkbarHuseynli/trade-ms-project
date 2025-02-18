package org.example.msorder.client.dto;

import lombok.Builder;
import org.example.msorder.model.enums.PaymentType;

import java.math.BigDecimal;

@Builder
public record PaymentRequest(Long orderId,

                             PaymentType paymentType,

                             BigDecimal amount

                     ) {
}
