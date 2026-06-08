package net.sarvesh.orderservice.dto;

import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(

        @NotNull
        Long userId,

        @NotNull
        Long restaurantId,

        @NotNull
        Double amount
) {
}