package com.miniecommerce.order_service.controller;

import com.miniecommerce.order_service.entity.Order;
import com.miniecommerce.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestParam Long productId,
                             @RequestParam Integer quantity) {

        Long userId = Long.parseLong(
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName()
        );

        return orderService.createOrder(productId, quantity, userId);
    }
}