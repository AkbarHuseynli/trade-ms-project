package org.example.mspayment.model.mapper;

import org.example.mspayment.entity.Payment;
import org.example.mspayment.model.dto.PaymentRequest;
import org.example.mspayment.model.dto.PaymentResponse;
import org.example.mspayment.model.enums.PaymentStatus;

import java.util.UUID;

public enum PaymentMapper {
    PAYMENT_MAPPER;

    public Payment paymentRequestToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .paymentType(paymentRequest.paymentType())
                .amount(paymentRequest.amount())
                .orderId(paymentRequest.orderId())
                .paymentStatus(PaymentStatus.PENDING)
                .transactionNumber(UUID.randomUUID())
                .build();
    }

    public PaymentResponse paymentToPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .paymentType(payment.getPaymentType())
                .paymentStatus(payment.getPaymentStatus())
                .amount(payment.getAmount())
                .transactionNumber(payment.getTransactionNumber())
                .createdAt(payment.getCreatedAt())
                .build();
    }


}
