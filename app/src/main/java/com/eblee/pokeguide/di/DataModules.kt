package com.eblee.pokeguide.di

import com.eblee.pokeguide.data.respository.PokemonLocalRepositoryImpl
import com.eblee.pokeguide.data.respository.PokemonRepositoryImpl
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import com.eblee.pokeguide.domain.repository.PokemonRepository
import org.koin.dsl.module

val dataModules = module {

    // RepositoryImpl
    single { PokemonRepositoryImpl(get()) as PokemonRepository }

    single { PokemonLocalRepositoryImpl(get()) as PokemonLocalRepository }
}