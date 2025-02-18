package org.example.products.model.enums;

import org.example.products.entity.Product;
import org.example.products.model.dto.ProductRequest;
import org.example.products.model.dto.ProductResponse;

public enum ProductMapper {

    PRODUCT_MAPPER;

    public Product productRequestToProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .stock(productRequest.stock())
                .build();

    }

    public ProductResponse productToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
