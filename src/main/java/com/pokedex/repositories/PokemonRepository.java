package com.pokedex.repositories;

import com.pokedex.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Pokemon findByPokemon(int pokemon);
    Pokemon deleteByPokemon(int pokemon);
}
