package com.miniecommerce.order_service.service;

import com.miniecommerce.order_service.client.ProductClient;
import com.miniecommerce.order_service.dto.ProductResponse;
import com.miniecommerce.order_service.entity.Order;
import com.miniecommerce.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final EmailService emailService;

    public Order createOrder(Long productId, Integer quantity, Long userId) {


        ProductResponse product = productClient.getProductById(productId);

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        Order order = Order.builder()
                .productId(productId)
                .quantity(quantity)
                .userId(userId)
                .status("CREATED")
                .build();

        //thread
        new Thread(() -> {
            try {
                emailService.sendOrderConfirmation(
                        "santxgaming123@gmail.com",   // change to dynamic user email if needed
                        product.getName(),
                        quantity,
                        product.getPrice()
                );
            } catch (Exception e) {
                System.out.println("Email sending failed: " + e.getMessage());
            }
        }).start();

        return orderRepository.save(order);
    }


}