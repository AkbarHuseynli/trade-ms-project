package org.example.msorder.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.msorder.client.PaymentClient;
import org.example.msorder.client.ProductClient;
import org.example.msorder.client.dto.PaymentResponse;
import org.example.msorder.entity.Order;
import org.example.msorder.exception.OrderNotFoundException;
import org.example.msorder.model.dto.OrderRequest;
import org.example.msorder.model.dto.OrderResponse;
import org.example.msorder.client.dto.PaymentRequest;
import org.example.msorder.client.dto.ProductDTO;
import org.example.msorder.model.enums.ErrorMessage;
import org.example.msorder.model.enums.OrderStatus;
import org.example.msorder.model.mapper.OrderMapper;
import org.example.msorder.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;


    public OrderResponse createOrder(OrderRequest orderRequest) {

        Order order = OrderMapper.OrderMapper.orderRequestToOrder(orderRequest);

        // estimating the total bill of the certain number of products
        BigDecimal priceOfProduct = productClient.getProductById(order.getProductId()).price();
        BigDecimal totalBill = priceOfProduct.multiply(BigDecimal.valueOf(order.getQuantity()));
        order.setAmount(totalBill);

        orderRepository.save(order);

        //reducing the stock after the purchase
        productClient.reduceStock(order.getProductId(), order.getQuantity());

        try {
            PaymentRequest paymentRequest = PaymentRequest.builder()
                    .paymentType(order.getPaymentType())
                    .orderId(order.getId())
                    .amount(order.getAmount())
                    .build();

            paymentClient.pay(paymentRequest);

            log.info("The bill got paid!");

            order.setOrderStatus(OrderStatus.APPROVED);

            log.info("Order has been approved successfully!");

        } catch (Exception e) {
            order.setOrderStatus(OrderStatus.REJECTED);
            log.error("Order got rejected due to some issues!");
        }

        return OrderMapper
                .OrderMapper
                .orderToOrderResponse(
                        orderRepository.save(order),
                        productClient.getProductById(order.getProductId()),
                        paymentClient.getPaymentByOrderId(order.getId())
                );

    }

    public OrderResponse getOrderById(Long id) {
        Order order = findOrderById(id);
        ProductDTO productDTO = productClient.getProductById(order.getProductId());
        PaymentResponse paymentResponse = paymentClient.getPaymentByOrderId(order.getId());
        return OrderMapper
                .OrderMapper
                .orderToOrderResponse(order, productDTO, paymentResponse);
    }

    private Order findOrderById(Long id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new OrderNotFoundException
                        (String.format(ErrorMessage.ORDER_NOT_FOUND.getMessage(), id)));

    }


}
