package org.example.products.repository;

import org.example.products.entity.Product;
import org.example.products.exception.ProductNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static org.example.products.model.enums.ErrorMessage.PRODUCT_NOT_FOUND;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
