package org.example.mspayment.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import org.example.mspayment.model.enums.PaymentStatus;
import org.example.mspayment.model.enums.PaymentType;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record PaymentResponse(
        Long id,
        PaymentType paymentType,
        PaymentStatus paymentStatus,
        BigDecimal amount,
        UUID transactionNumber,
        LocalDateTime createdAt


) {
}
