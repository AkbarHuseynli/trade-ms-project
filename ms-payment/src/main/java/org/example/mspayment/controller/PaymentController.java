package org.example.mspayment.controller;

import lombok.RequiredArgsConstructor;
import org.example.mspayment.model.dto.PaymentRequest;
import org.example.mspayment.model.dto.PaymentResponse;
import org.example.mspayment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PaymentResponse pay(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.pay(paymentRequest);
    }

    @GetMapping("order/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentResponse getPaymentByOrderId(@PathVariable Long orderId){
        return paymentService.getPaymentByOrderId(orderId);
    }

}
