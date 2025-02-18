package org.example.apigateway.controller;

import org.example.apigateway.model.FallBackResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/productServiceFallBack")
    public FallBackResponse productServiceFallBack() {
        return new FallBackResponse("Product service is down!");
    }

    @GetMapping("/orderServiceFallBack")
    public FallBackResponse orderServiceFallBack() {
        return new FallBackResponse("Order service is down!");
    }

    @GetMapping("/paymentServiceFallBack")
    public FallBackResponse paymentServiceFallBack() {
        return new FallBackResponse("Payment service is down!");
    }

}
