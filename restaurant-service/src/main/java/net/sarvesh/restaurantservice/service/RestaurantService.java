package net.sarvesh.restaurantservice.service;

import lombok.RequiredArgsConstructor;

import net.sarvesh.restaurantservice.dto.RestaurantRequest;
import net.sarvesh.restaurantservice.dto.RestaurantResponse;

import net.sarvesh.restaurantservice.entity.Restaurant;
import net.sarvesh.restaurantservice.repository.RestaurantRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @CacheEvict(value = "v2-restaurants-all", allEntries = true)
    public RestaurantResponse createRestaurant(RestaurantRequest request) {

        Restaurant restaurant = Restaurant.builder()
                .name(request.name())
                .address(request.address())
                .rating(request.rating())
                .build();

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantResponse.from(savedRestaurant);
    }

    @Cacheable(value = "v2-restaurants-all", key = "'all'")
    public List<RestaurantResponse> getAllRestaurants() {

        return restaurantRepository.findAll()
                .stream()
                .map(RestaurantResponse::from)
                .toList();
    }

    @Cacheable(value = "v2-restaurant-by-id", key = "#id")
    public RestaurantResponse getRestaurantById(Long id) {

        return restaurantRepository.findById(id)
                .map(RestaurantResponse::from)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Restaurant not found with id: " + id
                ));
    }

    @Caching(evict = {
            @CacheEvict(value = "v2-restaurants-all", allEntries = true),
            @CacheEvict(value = "v2-restaurant-by-id", key = "#id")
    })
    public void deleteRestaurant(Long id) {

        if (!restaurantRepository.existsById(id)) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Restaurant not found with id: " + id
            );
        }

        restaurantRepository.deleteById(id);
    }
}