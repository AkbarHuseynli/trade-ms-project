package org.example.msorder.client;

import org.example.msorder.client.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "ms-product",
        url = "http://localhost:8082/v1/products",
        configuration = CustomErrorDecoder.class
)
public interface ProductClient {

    @PostMapping("/reduce-stock")
    void reduceStock(@RequestParam Long productId, @RequestParam Integer reduceStock);

    @GetMapping("/{productId}")
    ProductDTO getProductById(@PathVariable Long productId);
}
