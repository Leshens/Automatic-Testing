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
public class Item {
    @Id
    @GeneratedValue
    private Long foodId;
    private Long userId;
    private String name;
    private String category;
    private String supplier;
    private Float quantity;
    private Float price;
}
