package com.eblee.pokeguide.domain.repository

import com.eblee.pokeguide.data.remote.entity.PokemonEntity
import com.eblee.pokeguide.data.remote.entity.PokemonResponse
import com.eblee.pokeguide.data.remote.entity.PokemonSpeciesEntity
import com.eblee.pokeguide.domain.entity.PokemonInfo
import io.reactivex.Single

interface PokemonRepository {

    fun getAllPokemon(): Single<PokemonResponse>

    fun getAllPokemonNextPage(offset: Int): Single<PokemonResponse>

    fun getPokemonInfo(id: Int): Single<PokemonInfo>

}