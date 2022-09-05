package com.eblee.pokeguide.domain.use_case.main

import com.eblee.pokeguide.domain.entity.Pokemon
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import com.eblee.pokeguide.domain.repository.PokemonRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class UCGetAllPokemonNextPage(
    private val pokemonRepository: PokemonRepository,
    private val pokemonLocalRepository: PokemonLocalRepository
) {

    fun invoke(offset: Int): Single<List<Pokemon>> {
        return Single.zip(
            pokemonRepository.getAllPokemonNextPage(offset).subscribeOn(Schedulers.io()),
            pokemonLocalRepository.getPokemonNames(offset).subscribeOn(Schedulers.io()),
        ) { response, names ->
            response.results.mapIndexed { index, resultEntity ->
                Pokemon(
                    names[index].name,
                    resultEntity.url,
                    null
                )
            }
        }
    }

}