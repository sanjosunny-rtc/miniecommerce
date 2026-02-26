package com.miniecommerce.order_service.client;

import com.miniecommerce.order_service.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8082")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponse getProductById(@PathVariable Long id);
}