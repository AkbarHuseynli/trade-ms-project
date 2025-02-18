package org.example.products.service;

import org.example.products.entity.Product;
import org.example.products.exception.InsufficientStockException;
import org.example.products.exception.ProductNotFoundException;
import org.example.products.model.dto.ProductRequest;
import org.example.products.model.dto.ProductResponse;
import org.example.products.model.enums.ErrorMessage;
import org.example.products.model.enums.ProductMapper;
import org.example.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import static org.example.products.model.enums.ErrorMessage.PRODUCT_NOT_FOUND;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productRepository
                .save(ProductMapper.PRODUCT_MAPPER.productRequestToProduct(productRequest));

        return ProductMapper.PRODUCT_MAPPER.productToProductResponse(product);
    }

    public ProductResponse getProductById(Long productId) {
        return ProductMapper
                .PRODUCT_MAPPER
                .productToProductResponse(findProductById(productId));
    }

    public void reduceStock(Long productId, Integer reduceStock) {

        Product prod = findProductById(productId);
        if (prod.getStock() < reduceStock) {
            throw new InsufficientStockException(
                    String.format(ErrorMessage.INSUFFICIENT_STOCK.getMessage(), productId));
        }
        prod.setStock(prod.getStock() - reduceStock);
        productRepository.save(prod);

    }

    private Product findProductById(Long productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format(PRODUCT_NOT_FOUND.getMessage(), productId)));
    }
}
