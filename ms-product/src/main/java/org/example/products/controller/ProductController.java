package org.example.products.controller;

import jakarta.validation.Valid;
import org.example.products.model.dto.ProductRequest;
import org.example.products.model.dto.ProductResponse;
import org.example.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //200 success, validation error,
    public ProductResponse createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/reduce-stock")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reduceStock(@RequestParam Long productId, @RequestParam Integer reduceStock) {
        productService.reduceStock(productId, reduceStock);
    }
}
