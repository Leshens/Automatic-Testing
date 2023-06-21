package com.RestaurantOptimize.RestaurantOptimizebackend.controller;


import com.RestaurantOptimize.RestaurantOptimizebackend.model.Order;
import com.RestaurantOptimize.RestaurantOptimizebackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class OrderContoller {
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/order")
    Order newOrder(@RequestBody Order newOrder){
        return orderRepository.save(newOrder);
    }
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllItems(){
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/orders/ready")
    public ResponseEntity<List<Order>> getAllReadyItems(){
        return new ResponseEntity<>(orderRepository.findByisReadyTrue(), HttpStatus.OK);
    }
    @GetMapping("/orders/notready")
    public ResponseEntity<List<Order>> getAllNotReadyItems(){
        return new ResponseEntity<>(orderRepository.findByisReadyFalse(), HttpStatus.OK);
    }
    @GetMapping("order/{id}")
    public ResponseEntity<Order> getById(@PathVariable long id) {

        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Order not found"
            );
        }
    }
    @PutMapping("/addOrder/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable long id,@RequestBody Order order) {
        Order updateOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"
                ));

        updateOrder.setUserId(order.getUserId());
        updateOrder.setReady(order.isReady());
        updateOrder.setIssued(order.isIssued());
        updateOrder.setOrderedAt(order.getOrderedAt());
        updateOrder.setPrice(order.getPrice());
        updateOrder.setTax(order.getTax());
        updateOrder.setFinalCost(order.getPrice()*(updateOrder.getTax()/100)) ;

        orderRepository.save(updateOrder);

        return ResponseEntity.ok(updateOrder);
    }
    @PutMapping("/updateOrderPrice/{id}/{price}")
    public ResponseEntity<Order> updateOrderPrice(@PathVariable long id,@PathVariable long price) {
        Order updateOrderPrice = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"
                ));
        updateOrderPrice.setPrice(updateOrderPrice.getPrice()+price);
        updateOrderPrice.setFinalCost(updateOrderPrice.getPrice()*(updateOrderPrice.getTax()/100)) ;

        orderRepository.save(updateOrderPrice);

        return ResponseEntity.ok(updateOrderPrice);
    }
    @PutMapping("/orderIsReady/{id}")
    public ResponseEntity<Order> orderIsReady(@PathVariable long id) {
        Order updateOrderPrice = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"
                ));

        updateOrderPrice.setReady(true);

        orderRepository.save(updateOrderPrice);

        return ResponseEntity.ok(updateOrderPrice);
    }
    @PutMapping("/orderIsIssued/{id}")
    public ResponseEntity<Order> orderIsIssued(@PathVariable long id) {
        Order updateOrderPrice = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"
                ));
        updateOrderPrice.setReady(false);
        updateOrderPrice.setIssued(true);

        orderRepository.save(updateOrderPrice);

        return ResponseEntity.ok(updateOrderPrice);
    }
    @DeleteMapping(value = "/deleteOrder/{id}")
    public ResponseEntity<Long> deleteOrder(@PathVariable Long id) {


        if (!orderRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Order not found"
            );
        }
        orderRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }
}
