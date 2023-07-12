package com.pokedex.services;

import com.pokedex.repositories.UserRepository;
import com.pokedex.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optUser = userRepository.findByUsername(username);

        if (optUser.isPresent()) {
            return new SecurityUser(optUser.get());
        }

        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
}
