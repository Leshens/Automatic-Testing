package com.RestaurantOptimize.RestaurantOptimizebackend.controller;


import com.RestaurantOptimize.RestaurantOptimizebackend.model.Item;
import com.RestaurantOptimize.RestaurantOptimizebackend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/item")
    Item newItem(@RequestBody Item newItem){
        return itemRepository.save(newItem);
    }
    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems(){
        return new ResponseEntity<>(itemRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("item/{id}")
    public ResponseEntity<Item> getById(@PathVariable long id) {

        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return new ResponseEntity<>(item.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Item not found"
            );
        }
    }
    @PutMapping("addItem/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable long id,@RequestBody Item item) {
        Item updateItem = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item not found"
                ));

        updateItem.setUserId(item.getUserId());
        updateItem.setName(item.getName());
        updateItem.setCategory(item.getCategory());
        updateItem.setSupplier(item.getSupplier());
        updateItem.setQuantity(item.getQuantity());
        updateItem.setPrice(item.getPrice());

        itemRepository.save(updateItem);

        return ResponseEntity.ok(updateItem);
    }
    @DeleteMapping(value = "/deleteItem/{id}")
    public ResponseEntity<Long> deleteItem(@PathVariable Long id) {


        if (!itemRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Item not found"
            );
        }
        itemRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }
}
