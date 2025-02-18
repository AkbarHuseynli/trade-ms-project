package org.example.msorder.model.mapper;

import org.example.msorder.client.dto.PaymentResponse;
import org.example.msorder.entity.Order;
import org.example.msorder.model.dto.OrderRequest;
import org.example.msorder.model.dto.OrderResponse;
import org.example.msorder.client.dto.ProductDTO;
import org.example.msorder.model.enums.OrderStatus;

import java.time.LocalDateTime;

public enum OrderMapper {

    OrderMapper;

    public Order orderRequestToOrder(OrderRequest orderRequest) {
        return Order.builder()
                .productId(orderRequest.productId())
                .quantity(orderRequest.quantity())
                .orderStatus(OrderStatus.PENDING)
                .paymentType(orderRequest.paymentType())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public OrderResponse orderToOrderResponse(Order order, ProductDTO productDTO, PaymentResponse paymentResponse) {
        return OrderResponse.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .amount(order.getAmount())
                .quantity(order.getQuantity())
                .orderStatus(order.getOrderStatus())
                .productDTO(productDTO)
                .paymentResponse(paymentResponse)
                .build();

    }

}
