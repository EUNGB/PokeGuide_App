package com.eblee.pokeguide.domain.use_case.main

import com.eblee.pokeguide.data.local.entity.PokemonNameEntity
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UCGetPokemonKoreanName(
    private val pokemonLocalRepository: PokemonLocalRepository
) {

    fun invoke(id: Int) : Single<PokemonNameEntity> {
        return pokemonLocalRepository.getPokemonNameById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}