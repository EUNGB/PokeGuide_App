package com.eblee.pokeguide.domain.use_case.main

import android.annotation.SuppressLint
import com.eblee.pokeguide.domain.entity.Pokemon
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import com.eblee.pokeguide.domain.repository.PokemonRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class UCGetAllPokemon(
    private val pokemonRepository: PokemonRepository,
    private val pokemonLocalRepository: PokemonLocalRepository
) {

    @SuppressLint("CheckResult")
    fun invoke(): Single<List<Pokemon>> {
        return Single.zip(
            pokemonRepository.getAllPokemon().subscribeOn(Schedulers.io()),
            pokemonLocalRepository.getPokemonNames().subscribeOn(Schedulers.io()),
        ) { response, names ->
            response.results.mapIndexed { index, resultEntity ->
                Pokemon(
                    names[index].name,
                    resultEntity.url,
                    null
                )
            }
        }

//        return pokemonRepository.getAllPokemon()
//            .map { it.results.map { res -> res.toMap() } }
    }

}