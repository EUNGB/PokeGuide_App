package com.eblee.pokeguide.domain.use_case.detail

import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import io.reactivex.Completable

class UCRemoveCatchPokemon(
    private val pokemonLocalRepository: PokemonLocalRepository
) {

    fun invoke(id: Int): Completable {
        return pokemonLocalRepository.removerCatchPokemon(id)
    }
}