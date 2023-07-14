package com.pokedex.controllers;

import com.pokedex.entities.User;
import com.pokedex.services.EmailService;
import com.pokedex.services.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public Optional<User> getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) throws MessagingException {
        emailService.sendSimpleMessage(user.getEmail());
        return userService.addUser(user);
    }

    @DeleteMapping("/user/{id}")
    public Optional<User> deleteUserById(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/auth/{id}")
    public Boolean authUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.get().getAuthorities().get(0).getName().toString().equals("WRITE");
    }

    @PostMapping("/tokenEmail/{id}/{key}")
    public Optional<User> tokenEmail(@PathVariable("id") int id,@PathVariable("key") int key) {
        return userService.getTokenEmail(key, id);
    }
}
