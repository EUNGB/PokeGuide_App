package com.eblee.pokeguide.data.api

import com.eblee.pokeguide.data.remote.entity.PokemonEntity
import com.eblee.pokeguide.data.remote.entity.PokemonResponse
import com.eblee.pokeguide.data.remote.entity.PokemonSpeciesEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("/api/v2/pokemon/")
    fun getAllPokemon(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): Single<PokemonResponse>

    @GET("/api/v2/pokemon/{id}")
    fun getPokemonInfo(
        @Path("id") id: Int
    ): Single<PokemonEntity>

    @GET("/api/v2/pokemon-species/{id}")
    fun getPokemonSpeciesById(
        @Path("id") id: Int
    ): Single<PokemonSpeciesEntity>

}