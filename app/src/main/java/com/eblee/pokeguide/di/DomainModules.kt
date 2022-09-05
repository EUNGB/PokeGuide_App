package com.eblee.pokeguide.di

import com.eblee.pokeguide.domain.use_case.UCSavePokemonNames
import com.eblee.pokeguide.domain.use_case.detail.UCGetPokemonInfo
import com.eblee.pokeguide.domain.use_case.main.UCGetAllPokemon
import com.eblee.pokeguide.domain.use_case.main.UCGetAllPokemonNextPage
import org.koin.dsl.module

val domainModules = module {

    // UseCase
    // Main
    single { UCGetAllPokemon(get(), get()) }
    single { UCGetAllPokemonNextPage(get(), get()) }

    single { UCGetPokemonInfo(get()) }

    // Splash
    single { UCSavePokemonNames(get()) }

}