package com.eblee.pokeguide.domain.use_case.detail

import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import com.eblee.pokeguide.domain.repository.PokemonRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UCGetPokemonInfo(
    private val pokemonRepository: PokemonRepository,
    private val pokemonLocalRepository: PokemonLocalRepository
) {
    fun invoke(id: Int): Single<PokemonInfo> {
        return Single.zip(
            pokemonRepository.getPokemonInfo(id).subscribeOn(Schedulers.io()),
            pokemonLocalRepository.getIsCatch(id).subscribeOn(Schedulers.io())
        ) { info, isCatch ->
            PokemonInfo(
                info.pokemonEntity,
                info.pokemonSpeciesEntity,
                isCatch
            )
        }
            .observeOn(AndroidSchedulers.mainThread())
    }
}