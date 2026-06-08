package net.sarvesh.orderservice.service;

import lombok.RequiredArgsConstructor;
import net.sarvesh.commonlib.event.OrderCreatedEvent;
import net.sarvesh.orderservice.dto.CreateOrderRequest;
import net.sarvesh.orderservice.dto.OrderResponse;
import net.sarvesh.orderservice.entity.FoodOrder;
import net.sarvesh.orderservice.entity.OrderStatus;
import net.sarvesh.orderservice.kafka.OrderEventPublisher;
import net.sarvesh.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderEventPublisher publisher;

    public FoodOrder createOrder(
            CreateOrderRequest request
    ) {

        FoodOrder order =
                FoodOrder.builder()
                        .userId(request.userId())
                        .restaurantId(request.restaurantId())
                        .amount(request.amount())
                        .status(OrderStatus.PAYMENT_PENDING)
                        .build();

        FoodOrder saved =
                repository.save(order);

        publisher.publish(
                new OrderCreatedEvent(
                        saved.getId(),
                        saved.getUserId(),
                        saved.getRestaurantId(),
                        saved.getAmount(),
                        Instant.now()
                )
        );

        return saved;
    }

    public OrderResponse getOrder(
            Long orderId
    ) {

        FoodOrder order =
                repository.findById(orderId)
                        .orElseThrow();

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getRestaurantId(),
                order.getAmount(),
                order.getStatus()
        );
    }
}