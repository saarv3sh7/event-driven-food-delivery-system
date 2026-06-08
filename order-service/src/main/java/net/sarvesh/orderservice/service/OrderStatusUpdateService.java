package net.sarvesh.orderservice.service;

import lombok.RequiredArgsConstructor;
import net.sarvesh.orderservice.entity.FoodOrder;
import net.sarvesh.orderservice.entity.OrderStatus;
import net.sarvesh.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatusUpdateService {

    private final OrderRepository orderRepository;

    public void markConfirmed(Long orderId) {

        FoodOrder order =
                orderRepository.findById(orderId)
                        .orElseThrow();

        order.setStatus(OrderStatus.CONFIRMED);

        orderRepository.save(order);
    }

    public void markFailed(Long orderId) {

        FoodOrder order =
                orderRepository.findById(orderId)
                        .orElseThrow();

        order.setStatus(OrderStatus.FAILED);

        orderRepository.save(order);
    }
}