package com.eblee.pokeguide.domain.entity

import com.eblee.pokeguide.data.remote.entity.PokemonEntity
import com.eblee.pokeguide.data.remote.entity.PokemonSpeciesEntity

data class PokemonInfo(
    val pokemonEntity: PokemonEntity,
    val pokemonSpeciesEntity: PokemonSpeciesEntity,
    var isCatch: Boolean = false
)
