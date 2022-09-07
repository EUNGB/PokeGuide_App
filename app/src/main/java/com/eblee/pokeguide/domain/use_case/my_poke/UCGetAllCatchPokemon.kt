package com.eblee.pokeguide.domain.use_case.my_poke

import com.eblee.pokeguide.data.local.entity.PokemonCatchEntity
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UCGetAllCatchPokemon(
    private val pokemonLocalRepository: PokemonLocalRepository
) {
    fun invoke() : Single<List<PokemonCatchEntity>> {
        return pokemonLocalRepository.getAllCatchPokemon()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}