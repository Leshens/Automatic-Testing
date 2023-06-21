package com.RestaurantOptimize.RestaurantOptimizebackend.repository;

import com.RestaurantOptimize.RestaurantOptimizebackend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
