package com.pokedex.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pokemons")
public class Pokemon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    int id;

    @Getter @Setter
    int pokemon;
}
