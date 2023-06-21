package com.RestaurantOptimize.RestaurantOptimizebackend.controller;

import com.RestaurantOptimize.RestaurantOptimizebackend.model.User;
import com.RestaurantOptimize.RestaurantOptimizebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user not found"
            );
        }
    }
    @GetMapping("/{email}")
    public ResponseEntity<User> LoginUser(@PathVariable("email") String email) {

        Optional<User> user =userRepository.findByEmail(email);
        if (user.isPresent())
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user or password are not correct");
    }
    @PutMapping("/adduser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id,@RequestBody User user) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "user not found"
                ));

        updateUser.setFirstName(user.getFirstName());
        updateUser.setMiddleName(user.getMiddleName());
        updateUser.setLastName(user.getLastName());
        updateUser.setMobileNumber(user.getMobileNumber());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        updateUser.setRegisteredAt(user.getRegisteredAt());
        updateUser.setLastLogIn(user.getLastLogIn());


        userRepository.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }
    @DeleteMapping(value = "/deleteuser/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {


        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user not found"
            );
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }
}
