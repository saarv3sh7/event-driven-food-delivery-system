package net.sarvesh.orderservice.repository;

import net.sarvesh.orderservice.entity.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<FoodOrder, Long> {
}