package org.example.products.model.enums;

import org.assertj.core.api.Assertions;
import org.example.products.entity.Product;
import org.example.products.model.dto.ProductRequest;
import org.example.products.model.dto.ProductResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class ProductMapperTest {

    @Test
    void ToProductTest() {

        //arrange
        var request = ProductRequest.builder()
                .name("iphone 16")
                .description("made in usa")
                .stock(15)
                .price(BigDecimal.valueOf(1500))
                .build();

        var expected = Product.builder()
                .name("iphone 16")
                .description("made in usa")
                .stock(15)
                .price(BigDecimal.valueOf(1500))
                .build();

        //actional

        var actual = ProductMapper.PRODUCT_MAPPER.productRequestToProduct(request);

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    void toProductResponseTest(){
        //arrange
        var request = Product.builder()
                .id(1L)
                .name("iphone 17")
                .description("made in china")
                .price(BigDecimal.valueOf(3600))
                .stock(10)
                .build();

        var expected = ProductResponse.builder()
                .id(1L)
                .name("iphone 17")
                .description("made in china")
                .price(BigDecimal.valueOf(3600))
                .stock(10)
                .build();

        //actional
        var actual = ProductMapper.PRODUCT_MAPPER.productToProductResponse(request);

        //assertion

        Assertions.assertThat(actual).isEqualTo(expected);

    }
}