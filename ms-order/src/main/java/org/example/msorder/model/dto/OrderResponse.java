package org.example.msorder.model.dto;

import lombok.Builder;
import org.example.msorder.client.dto.PaymentResponse;
import org.example.msorder.client.dto.ProductDTO;
import org.example.msorder.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record OrderResponse(Long id,
                            ProductDTO productDTO,
                            PaymentResponse paymentResponse,
                            Integer quantity,
                            OrderStatus orderStatus,
                            BigDecimal amount,
                            LocalDateTime createdAt) {
    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + id +
                ", product=" + productDTO +
                ", payment=" + paymentResponse +
                ", quantity=" + quantity +
                ", orderStatus=" + orderStatus +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
