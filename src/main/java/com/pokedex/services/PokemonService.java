package com.pokedex.services;

import com.pokedex.entities.Pokemon;
import com.pokedex.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    public Pokemon addPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public Optional<Pokemon> findPokemonByPokemon(int pokemon) {
        return Optional.ofNullable(pokemonRepository.findByPokemon(pokemon));
    }

    public Optional<Pokemon> deletePokemonByPokemon(int pokemon) {
        Optional<Pokemon> tempPokemon = findPokemonByPokemon(pokemon);
        Long tempId = (long) tempPokemon.get().getId();
        pokemonRepository.deleteById(tempId);
        return tempPokemon;
    }

    public Optional<Pokemon> deletePokemonById(Long id) {
        Optional<Pokemon> pok = pokemonRepository.findById(id);
        pokemonRepository.deleteById(id);
        return pok;
    }
}
