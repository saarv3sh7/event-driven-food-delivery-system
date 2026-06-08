package net.sarvesh.restaurantservice.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestaurantRequest(

        @NotBlank
        String name,

        @NotBlank
        String address,

        @NotNull
        @DecimalMin("0.0")
        @DecimalMax("5.0")
        Double rating
) {
}