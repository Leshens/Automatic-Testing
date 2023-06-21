package com.RestaurantOptimize.RestaurantOptimizebackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue
    private Long orderItemId;
    private Long orderId;
    private Long dishId;
}
