package com.miniecommerce.product_service.controller;

import com.miniecommerce.product_service.entity.Product;
import com.miniecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @PutMapping("/{id}")   // 🔴 Added
    public Product update(@PathVariable Long id,
                          @RequestBody Product product) {
        return service.update(id, product);
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}