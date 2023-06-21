package com.RestaurantOptimize.RestaurantOptimizebackend.controller;

import com.RestaurantOptimize.RestaurantOptimizebackend.model.Dish;
import com.RestaurantOptimize.RestaurantOptimizebackend.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class DishController {

    @Autowired
    private DishRepository dishRepository;

    @PostMapping("/dish")
    Dish newDish(@RequestBody Dish newDish){
        return dishRepository.save(newDish);
    }
    @GetMapping("/dishes")
    public ResponseEntity<List<Dish>> getAllDishes(){
        return new ResponseEntity<>(dishRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("dish/{id}")
    public ResponseEntity<Dish> getById(@PathVariable long id) {

        Optional<Dish> dish = dishRepository.findById(id);
        if (dish.isPresent()) {
            return new ResponseEntity<>(dish.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Dish not found"
            );
        }
    }
    @PutMapping("addDish/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable long id,@RequestBody Dish dish) {
        Dish updateDish = dishRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Dish not found"
                ));

        updateDish.setUserId(dish.getUserId());
        updateDish.setName(dish.getName());
        updateDish.setCategory(dish.getCategory());
        updateDish.setPrice(dish.getPrice());

        dishRepository.save(updateDish);

        return ResponseEntity.ok(updateDish);
    }
    @DeleteMapping(value = "/deleteDish/{id}")
    public ResponseEntity<Long> deleteDish(@PathVariable Long id) {


        if (!dishRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Dish not found"
            );
        }
        dishRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }
}
