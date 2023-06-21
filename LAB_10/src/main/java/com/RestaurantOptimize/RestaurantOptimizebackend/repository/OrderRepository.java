package com.RestaurantOptimize.RestaurantOptimizebackend.repository;

import com.RestaurantOptimize.RestaurantOptimizebackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByisReadyTrue();

    List<Order> findByisReadyFalse();

    List<Order> findByisIssuedFalse();
}
