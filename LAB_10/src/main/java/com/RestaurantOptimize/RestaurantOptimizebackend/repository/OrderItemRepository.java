package com.RestaurantOptimize.RestaurantOptimizebackend.repository;

import com.RestaurantOptimize.RestaurantOptimizebackend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findAllByOrderId(long orderId);
}
