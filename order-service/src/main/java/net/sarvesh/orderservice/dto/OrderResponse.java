package net.sarvesh.orderservice.dto;

import net.sarvesh.orderservice.entity.OrderStatus;

public record OrderResponse(

        Long id,

        Long userId,

        Long restaurantId,

        Double amount,

        OrderStatus status
) {
}