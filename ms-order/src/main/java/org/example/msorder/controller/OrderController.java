package org.example.msorder.controller;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.msorder.exception.ServiceUnavailableException;
import org.example.msorder.model.dto.OrderRequest;
import org.example.msorder.model.dto.OrderResponse;
import org.example.msorder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse orderCreate(@RequestBody @Valid OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("/{orderId}")
    @CircuitBreaker(name ="getOrderById", fallbackMethod = "fallback")
    public OrderResponse getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    public OrderResponse fallback(Long response, Exception exception) {
        throw new ServiceUnavailableException("Service is currently unavailable");
    }

}
