package com.miniecommerce.product_service.service;

import com.miniecommerce.product_service.entity.Product;
import com.miniecommerce.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product create(Product product) {

        Long currentVendorId = getCurrentUserId();
        product.setVendorId(currentVendorId);

        return repository.save(product);
    }

    public Product update(Long id, Product updatedProduct) {

        Product existing = getById(id);

        validateOwnership(existing);

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setQuantity(updatedProduct.getQuantity());

        return repository.save(existing);
    }

    public void delete(Long id) {

        Product existing = getById(id);

        validateOwnership(existing);

        repository.delete(existing);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id: " + id));
    }

    // Ownership Validation
    private void validateOwnership(Product product) {

        Long currentUserId = getCurrentUserId();

        if (!product.getVendorId().equals(currentUserId)) {
            throw new RuntimeException("You can modify only your own products");
        }
    }

    // Extract userId from JWT
    private Long getCurrentUserId() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        // This depends on how you stored user details in JWT
        // Example: if username = userId
        return Long.parseLong(authentication.getName());
    }
}