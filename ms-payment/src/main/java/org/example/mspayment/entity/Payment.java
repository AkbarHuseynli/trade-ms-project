package org.example.mspayment.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.mspayment.model.enums.PaymentStatus;
import org.example.mspayment.model.enums.PaymentType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Table(name = "payments")
@Entity
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long orderId;

    @Enumerated(EnumType.STRING)
    PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;

    BigDecimal amount;

    UUID transactionNumber;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(orderId, payment.orderId) && paymentType == payment.paymentType && paymentStatus == payment.paymentStatus && Objects.equals(amount, payment.amount) && Objects.equals(transactionNumber, payment.transactionNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, paymentType, paymentStatus, amount, transactionNumber);
    }
}