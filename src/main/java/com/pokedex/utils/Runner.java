package com.pokedex.utils;

import com.pokedex.entities.Authority;
import com.pokedex.entities.User;
import com.pokedex.repositories.AuthorityRepository;
import com.pokedex.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Transactional
@Component
public class Runner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public Runner(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.authorityRepository.count() == 0) {
            this.authorityRepository.saveAll(List.of(
                    new Authority(AuthorityName.ADMIN),
                    new Authority(AuthorityName.READ),
                    new Authority(AuthorityName.WRITE)
            ));
        }

        if (this.userRepository.count() == 0) {
            this.userRepository.saveAll(List.of(
                    new User("usuario1", new BCryptPasswordEncoder().encode("password1"), "user@gmail.com", List.of(this.authorityRepository.findByName(AuthorityName.READ).get())),
                    new User("usuario2", new BCryptPasswordEncoder().encode("password2"), "user@gmail.com",  List.of(this.authorityRepository.findByName(AuthorityName.READ).get())),
                    new User("usuario3", new BCryptPasswordEncoder().encode("password3"), "user@gmail.com", List.of(this.authorityRepository.findByName(AuthorityName.WRITE).get())),
                    new User("usuario4", new BCryptPasswordEncoder().encode("password4"), "user@gmail.com", List.of(this.authorityRepository.findByName(AuthorityName.WRITE).get()))
            ));
        }
    }
}
