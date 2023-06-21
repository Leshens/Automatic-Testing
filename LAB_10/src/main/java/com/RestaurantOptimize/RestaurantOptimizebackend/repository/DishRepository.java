package com.RestaurantOptimize.RestaurantOptimizebackend.repository;

import com.RestaurantOptimize.RestaurantOptimizebackend.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish,Long> {
}
