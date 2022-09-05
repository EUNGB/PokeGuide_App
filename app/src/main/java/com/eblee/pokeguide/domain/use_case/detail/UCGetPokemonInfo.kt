package com.eblee.pokeguide.domain.use_case.detail

import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import com.eblee.pokeguide.domain.repository.PokemonRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class UCGetPokemonInfo(
    private val pokemonRepository: PokemonRepository
) {
    fun invoke(id: Int): Single<PokemonInfo> {
        return pokemonRepository.getPokemonInfo(id)
    }
}