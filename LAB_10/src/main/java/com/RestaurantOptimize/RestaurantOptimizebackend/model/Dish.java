package com.RestaurantOptimize.RestaurantOptimizebackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue
    private Long dishId;
    private Long userId;
    private String name;
    private String category;
    private Float price;
}
