package com.eblee.pokeguide.domain.use_case.main

import com.eblee.pokeguide.data.remote.entity.ResultEntity.Companion.toMap
import com.eblee.pokeguide.domain.entity.Pokemon
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import com.eblee.pokeguide.domain.repository.PokemonRepository
import io.reactivex.Single

class UCGetAllPokemon(
    private val pokemonRepository: PokemonRepository,
) {

    fun invoke(): Single<List<Pokemon>> {
        return pokemonRepository.getAllPokemon()
            .map { it.results.map { res -> res.toMap() } }
    }

}