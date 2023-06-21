package com.RestaurantOptimize.RestaurantOptimizebackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;
    private Long userId;
    private boolean isReady;
    private boolean isIssued;
    private String orderedAt;
    private Float price;
    private Float tax;
    private Float finalCost;
}
