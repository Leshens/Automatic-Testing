package com.RestaurantOptimize.RestaurantOptimizebackend.controller;

import com.RestaurantOptimize.RestaurantOptimizebackend.model.OrderItem;
import com.RestaurantOptimize.RestaurantOptimizebackend.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class OrderItemController {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @PostMapping("/orderItem")
    OrderItem newOrderItem(@RequestBody OrderItem newOrderItem){
        return orderItemRepository.save(newOrderItem);
    }
    @GetMapping("/orderItems")
    public ResponseEntity<List<OrderItem>> getAllItems(){
        return new ResponseEntity<>(orderItemRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/orderItemsByOrderId/{id}")
    public ResponseEntity<List<OrderItem>> getAllItemsByOrderId(@PathVariable long id){
        return new ResponseEntity<>(orderItemRepository.findAllByOrderId(id), HttpStatus.OK);
    }
    @GetMapping("orderItem/{id}")
    public ResponseEntity<OrderItem> getById(@PathVariable long id) {

        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (orderItem.isPresent()) {
            return new ResponseEntity<>(orderItem.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Order not found"
            );
        }
    }
    @PutMapping("addOrderItem/{id}")
    public ResponseEntity<OrderItem> updateItem(@PathVariable long id,@RequestBody OrderItem orderItem) {
        OrderItem updateOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"
                ));

        updateOrderItem.setOrderId(orderItem.getOrderId());
        updateOrderItem.setDishId(orderItem.getDishId());

        orderItemRepository.save(updateOrderItem);

        return ResponseEntity.ok(updateOrderItem);
    }
    @DeleteMapping(value = "/deleteOrderItem/{id}")
    public ResponseEntity<Long> deleteOrder(@PathVariable Long id) {


        if (!orderItemRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Order not found"
            );
        }
        orderItemRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }
}
