package com.eblee.pokeguide.domain.use_case.detail

import android.annotation.SuppressLint
import com.eblee.pokeguide.data.local.entity.PokemonCatchEntity
import com.eblee.pokeguide.data.remote.entity.PokemonEntity.Companion.getImgUrl
import com.eblee.pokeguide.data.remote.entity.PokemonSpeciesEntity.Companion.getKoreanName
import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UCCatchPokemon(
    private val pokemonLocalRepository: PokemonLocalRepository
) {

    @SuppressLint("CheckResult")
    fun invoke(pokemonInfo: PokemonInfo): Completable {
        return pokemonLocalRepository.insertCatchPokemon(
            PokemonCatchEntity(
                pokemonInfo.pokemonEntity.id,
                pokemonInfo.pokemonSpeciesEntity.names.getKoreanName(),
                pokemonInfo.pokemonEntity.getImgUrl(),
                pokemonInfo.pokemonEntity.types.first().type.name
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}