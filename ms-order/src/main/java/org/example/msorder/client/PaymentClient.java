package org.example.msorder.client;

import org.example.msorder.client.dto.PaymentRequest;
import org.example.msorder.client.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-payment",
        url = "http://localhost:8083/v1/payments",
        configuration = CustomErrorDecoder.class)
public interface PaymentClient {

    @PostMapping
    PaymentResponse pay(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("order/{orderId}")
    PaymentResponse getPaymentByOrderId(@PathVariable Long orderId);

}
