package com.eblee.pokeguide.di

import com.eblee.pokeguide.presentation.view.detail.DetailViewModel
import com.eblee.pokeguide.presentation.view.main.MainViewModel
import com.eblee.pokeguide.presentation.view.my_poke.MyPokemonViewModel
import com.eblee.pokeguide.presentation.view.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {

    // ViewModel
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { DetailViewModel(get(), get(), get()) }
    viewModel { MyPokemonViewModel(get()) }
}
