package com.eblee.pokeguide.domain.use_case.main

import com.eblee.pokeguide.domain.entity.Pokemon
import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import com.eblee.pokeguide.domain.repository.PokemonRepository
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UCGetAllPokemonNextPage(
    private val pokemonRepository: PokemonRepository,
    private val pokemonLocalRepository: PokemonLocalRepository
) {

    fun invoke(offset: Int): Single<List<PokemonInfo>> {
        val request: ArrayList<Single<PokemonInfo>> = ArrayList()
        for (id in offset..offset + 20) {
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