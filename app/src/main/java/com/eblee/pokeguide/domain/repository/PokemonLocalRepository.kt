package com.eblee.pokeguide.domain.repository

import com.eblee.pokeguide.data.local.entity.PokemonNameEntity
import io.reactivex.Single

interface PokemonLocalRepository {

    fun insertPokemonKoreanName(pokemonNameEntity: PokemonNameEntity)

    fun getPokemonNameById(id: Int): Single<PokemonNameEntity>
}