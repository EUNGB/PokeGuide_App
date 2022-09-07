package com.eblee.pokeguide.domain.repository

import com.eblee.pokeguide.data.local.entity.PokemonCatchEntity
import com.eblee.pokeguide.data.local.entity.PokemonNameEntity
import io.reactivex.Completable
import io.reactivex.Single

interface PokemonLocalRepository {

    fun insertPokemonKoreanName(pokemonNameEntity: PokemonNameEntity)

    fun getPokemonNames(offset: Int = 0): Single<List<PokemonNameEntity>>

    fun insertCatchPokemon(pokemonCatchEntity: PokemonCatchEntity): Completable

    fun getIsCatch(id: Int): Single<Boolean>

    fun removerCatchPokemon(id: Int): Completable

    fun getAllCatchPokemon() : Single<List<PokemonCatchEntity>>
}