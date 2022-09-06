package com.eblee.pokeguide.data.respository

import com.eblee.pokeguide.data.api.PokemonApi
import com.eblee.pokeguide.data.remote.entity.PokemonEntity
import com.eblee.pokeguide.data.remote.entity.PokemonResponse
import com.eblee.pokeguide.data.remote.entity.PokemonSpeciesEntity
import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.repository.PokemonRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PokemonRepositoryImpl(
    private val api: PokemonApi
) : PokemonRepository {
    override fun getAllPokemon(): Single<PokemonResponse> {
        return api.getAllPokemon()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllPokemonNextPage(offset: Int): Single<PokemonResponse> {
        return api.getAllPokemon(offset = offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPokemonInfo(id: Int): Single<PokemonInfo> {
        return Single.zip (
            api.getPokemonInfo(id).subscribeOn(Schedulers.io()),
            api.getPokemonSpeciesById(id).subscribeOn(Schedulers.io())
        ) { info, species ->
            PokemonInfo(info, species)
        }
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getSearchPokemonByName(name: String): Single<PokemonInfo> {
        return Single.zip (
            api.getPokemonInfo(name).subscribeOn(Schedulers.io()),
            api.getPokemonSpeciesById(name).subscribeOn(Schedulers.io())
        ) { info, species ->
            PokemonInfo(info, species)
        }
            .observeOn(AndroidSchedulers.mainThread())
    }

}