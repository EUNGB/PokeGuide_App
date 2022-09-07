package com.eblee.pokeguide.domain.use_case.main

import android.annotation.SuppressLint
import android.util.Log
import com.eblee.pokeguide.data.remote.entity.PokemonEntity
import com.eblee.pokeguide.domain.entity.Pokemon
import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import com.eblee.pokeguide.domain.repository.PokemonRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList


class UCGetAllPokemon(
    private val pokemonRepository: PokemonRepository,
    private val pokemonLocalRepository: PokemonLocalRepository
) {

    @SuppressLint("CheckResult")
    fun invoke(): Single<List<PokemonInfo>> {
        val request: ArrayList<Single<PokemonInfo>> = ArrayList()
        for (id in 1..20) {
            request.add(pokemonRepository.getPokemonInfo(id).subscribeOn(Schedulers.io()))
        }

        return Single.zip(request) { it ->
            it.toList() as List<PokemonInfo>
        }
            .subscribeOn(Schedulers.io())
            .flatMapPublisher {
                Flowable.fromIterable(it)
            }
            .flatMapSingle { pokemon ->
                pokemonLocalRepository.getIsCatch(pokemon.pokemonEntity.id)
                    .subscribeOn(Schedulers.io())
                    .map {
                        PokemonInfo(
                            pokemon.pokemonEntity,
                            pokemon.pokemonSpeciesEntity,
                            it
                        )
                    }
            }
            .toList()
            .map { list ->
                list.sortedBy { it.pokemonEntity.id }
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

}