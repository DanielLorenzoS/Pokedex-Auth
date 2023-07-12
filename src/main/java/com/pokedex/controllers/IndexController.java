package com.pokedex.controllers;

import com.pokedex.entities.Pokemon;
import com.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping("/")
    public List<Pokemon> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }

    @PostMapping("/")
    public Pokemon addPokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.addPokemon(pokemon);
    }

    @GetMapping("/{id}")
    public Optional<Pokemon> getPokemonByPokemon(@PathVariable("id") int id) {
        return pokemonService.findPokemonByPokemon(id);
    }

    @DeleteMapping("/{id}")
    public Optional<Pokemon> deletePokemonByPokemon(@PathVariable("id") int id) {
        return pokemonService.deletePokemonById(id);
    }

    @GetMapping("/auth")
    public Boolean authUser() {
        return true;
    }

}
