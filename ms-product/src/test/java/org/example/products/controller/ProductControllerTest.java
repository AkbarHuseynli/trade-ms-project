package org.example.products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.example.products.exception.ProductNotFoundException;
import org.example.products.model.dto.ProductRequest;
import org.example.products.model.dto.ProductResponse;
import org.example.products.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;

import static org.example.products.model.enums.ErrorMessage.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    private static final String PRODUCT_API_PATH = "/v1/products";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void createProduct_SuccessTest() throws Exception {
        // arrange
        var request = ProductRequest.builder()
                .name("iphone 16")
                .description("made in usa")
                .stock(15)
                .price(BigDecimal.valueOf(1500))
                .build();

        var response = ProductResponse
                .builder()
                .name("iphone 16")
                .description("made in usa")
                .stock(15)
                .price(BigDecimal.valueOf(1500))
                .build();

        //Actional
        when(productService.createProduct(request)).thenReturn(response);

        mockMvc.perform(post(PRODUCT_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

    }

    @Test
    void createProduct_WhenStockIsAddedLessThanOne_BadRequest() throws Exception {
        // arrange
        var request = ProductRequest.builder()
                .name("iphone 16")
                .description("made in usa")
                .stock(0)
                .price(BigDecimal.valueOf(1500))
                .build();

        //Actional
//        when(productService.createProduct(request)).thenThrow(MethodArgumentNotValidException.class);

        mockMvc.perform(post(PRODUCT_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createProduct_WhenDescriptionIsBlank_BadRequest() throws Exception {
        // arrange
        var request = ProductRequest.builder()
                .name("iphone 16")
                .description("")
                .stock(1)
                .price(BigDecimal.valueOf(1500))
                .build();

        //Action
//        when(productService.createProduct(request)).thenThrow(MethodArgumentNotValidException.class);

        mockMvc.perform(post(PRODUCT_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createProduct_WhenNameIsBlank_BadRequest() throws Exception {
        // arrange
        var request = ProductRequest.builder()
                .name("")
                .description("description")
                .stock(1)
                .price(BigDecimal.valueOf(1500))
                .build();

//        //Actional
//        when(productService.createProduct(request))
//                .thenThrow(ErrorResponse
//                        .builder(MethodArgumentNotValidException.class.newInstance(),
//                                HttpStatusCode.valueOf(404), "" ));

//        Assertions.assertThatThrownBy(()->productService.createProduct(request))
//                        .isInstanceOf(MethodArgumentNotValidException.class);

        mockMvc.perform(post(PRODUCT_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(productService);
    }

    @Test
    void getProductById_Success() throws Exception {
        //Arrange
        var ID = 1L;
        var response = ProductResponse.builder()
                .id(ID)
                .name("product")
                .description("description")
                .stock(1)
                .price(BigDecimal.valueOf(1500))
                .build();

        when(productService.getProductById(ID)).thenReturn(response);

        //Action
        mockMvc.perform(get(PRODUCT_API_PATH + "/{productId}", ID))
                .andExpect(jsonPath("$.name", Matchers.is("product")))
                .andExpect(status().isOk());

        verify(productService, times(1)).getProductById(ID);
    }

    @Test
    void getProductById_NotFound() throws Exception {
        //Arrange
        var ID = 1L;

        Mockito.when(productService.getProductById(ID))
                .thenThrow(new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND.getMessage(), ID)));

        Assertions.assertThatThrownBy(() -> productService.getProductById(ID))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage(String.format(PRODUCT_NOT_FOUND.getMessage(), ID));

        //Actional
        mockMvc.perform(get(PRODUCT_API_PATH + "/{productId}", ID))
                .andExpect(status().isNotFound());

        verify(productService, times(2)).getProductById(ID);
    }


    @Test
    void reduceStock_Success() throws Exception {

        //Arrange
        var ID = 1L;
        var reduceStock = 1;

        //Action
        mockMvc.perform(post(PRODUCT_API_PATH + "/reduce-stock")
                .param("productId", "1")
                .param("reduceStock", "1")
        ).andExpect(status().isNoContent());

    }
}