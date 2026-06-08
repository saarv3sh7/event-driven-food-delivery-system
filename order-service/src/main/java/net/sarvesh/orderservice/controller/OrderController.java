package net.sarvesh.orderservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sarvesh.orderservice.dto.CreateOrderRequest;
import net.sarvesh.orderservice.dto.OrderResponse;
import net.sarvesh.orderservice.entity.FoodOrder;
import net.sarvesh.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public FoodOrder createOrder(
            @Valid @RequestBody CreateOrderRequest request
    ) {
        return service.createOrder(request);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrder(
            @PathVariable Long id
    ) {

        return service.getOrder(id);
    }
}