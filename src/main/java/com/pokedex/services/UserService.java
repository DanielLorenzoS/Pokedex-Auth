package com.pokedex.services;

import com.pokedex.entities.Authority;
import com.pokedex.entities.User;
import com.pokedex.repositories.AuthorityRepository;
import com.pokedex.repositories.UserRepository;
import com.pokedex.utils.AuthorityName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    EmailService emailService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User addUser(User user) {
        var encodedPassword = user.getPassword();
        encodedPassword = new BCryptPasswordEncoder().encode(encodedPassword);
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public Optional<User> deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.deleteById(id);
        return user;
    }

    public Optional<User> getTokenEmail(int token, int id) {
        var random = emailService.randomNumber;
        System.out.println("random " + random);
        System.out.println("enviado " + token);
        if (random == token) {
            Optional<User> user = getUserById((long) id);
            var idAuth = user.get().getAuthorities().get(0).getId();
            Authority authority = authorityRepository.getById(idAuth);
            authority.setName(AuthorityName.valueOf("WRITE"));
            authorityRepository.save(authority);
            /* Optional<User> tempUser = Optional.of(new User());
            tempUser = user;
            var idAuth = tempUser.get().getAuthorities().get(0).getId();
            Authority authority = authorityRepository.getById(idAuth);
            authority.setName(AuthorityName.valueOf("WRITE"));
            tempUser.get().setAuthorities((List<Authority>) authority);
            user = tempUser;
            userRepository.save(user.get()); */
            return user;
        }
        return null;
    }
}
