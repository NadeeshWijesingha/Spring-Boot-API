package com.example.springboot.controller;

import com.example.springboot.repository.UserRepository;
import com.example.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // find all
    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    // find by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") int userId){
        return this.userRepository.findById(userId).get();
    }

    // save
    @PostMapping
    public User saveUser(@RequestBody User user){
        return this.userRepository.save(user);
    }

    // update
    @PutMapping("{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") int userId){
        User existingUser = this.userRepository.findById(userId).get();
        existingUser.setName(user.getName());
        return this.userRepository.save(existingUser);
    }

    // delete
    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") int userId){
        User existingUser = this.userRepository.findById(userId).get();
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }

}
