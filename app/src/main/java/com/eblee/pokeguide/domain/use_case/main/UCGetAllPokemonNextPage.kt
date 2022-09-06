package com.eblee.pokeguide.domain.use_case.main

import com.eblee.pokeguide.domain.entity.Pokemon
import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import com.eblee.pokeguide.domain.repository.PokemonRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UCGetAllPokemonNextPage(
    private val pokemonRepository: PokemonRepository
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
            .observeOn(AndroidSchedulers.mainThread())
    }

}