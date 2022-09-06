package com.eblee.pokeguide.domain.use_case.main

import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.repository.PokemonRepository
import io.reactivex.Single

class UCSearchPokemon(
    private val pokemonRepository: PokemonRepository
) {
    fun invoke(id: Int): Single<PokemonInfo> {
        return pokemonRepository.getPokemonInfo(id)
    }

    fun invoke(name: String): Single<PokemonInfo> {
        return pokemonRepository.getSearchPokemonByName(name)
    }
}