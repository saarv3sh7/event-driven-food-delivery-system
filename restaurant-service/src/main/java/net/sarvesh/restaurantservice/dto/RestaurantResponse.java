package net.sarvesh.restaurantservice.dto;

import net.sarvesh.restaurantservice.entity.Restaurant;

import java.io.Serializable;

public record RestaurantResponse(
        Long id,
        String name,
        String address,
        Double rating,
        Boolean active
) implements Serializable {

    public static RestaurantResponse from(Restaurant restaurant) {

        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getRating(),
                restaurant.getActive()
        );
    }
}