package com.pokedex.controllers;

import com.pokedex.entities.User;
import com.pokedex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public Optional<User> getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }
}
