package com.eblee.pokeguide.presentation.view.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.eblee.pokeguide.domain.entity.Pokemon
import com.eblee.pokeguide.domain.use_case.detail.UCGetPokemonInfo
import com.eblee.pokeguide.domain.use_case.main.UCGetAllPokemon
import com.eblee.pokeguide.domain.use_case.main.UCGetAllPokemonNextPage
import com.eblee.pokeguide.domain.use_case.main.UCGetPokemonKoreanName
import com.eblee.pokeguide.presentation.base.BaseViewModel

class MainViewModel(
    private val getAllPokemonUseCase: UCGetAllPokemon,
    private val getAllPokemonNextPage: UCGetAllPokemonNextPage,
    private val getPokemonKoreanName: UCGetPokemonKoreanName,
    private val getPokemonInfo: UCGetPokemonInfo
) : BaseViewModel() {

    val pokemonListLiveData = MutableLiveData<List<Pokemon>>()
    private var mPokemonList = mutableListOf<Pokemon>()

    val input = object : MainInput {
        override fun onLoad() {
            return getAllPokemon()
        }

        override fun onNextPage(offset: Int) {
            return getAllPokemonNextPage(offset)
        }
    }

    val output = object : MainOutput {
        override fun displayPokemonList(pokemonList: List<Pokemon>) {
            pokemonList.map {
                getPokemonKoreanName.invoke(it.getId()).subscribe { res -> it.name = res.name }
            }
            pokemonListLiveData.value = pokemonList
        }
    }

    @SuppressLint("CheckResult")
    fun getAllPokemon() {
        getAllPokemonUseCase.invoke()
            .subscribe({
                mPokemonList = it.toMutableList()
                output.displayPokemonList(it)
            }) {
                it.printStackTrace()
                Log.e("TAG", "getAllPokemon: ${it.message}")
            }
    }

    @SuppressLint("CheckResult")
    fun getAllPokemonNextPage(offset: Int) {
        getAllPokemonNextPage.invoke(offset)
            .subscribe({
                mPokemonList.addAll(it)
                output.displayPokemonList(mPokemonList)
            }) {
                it.printStackTrace()
                Log.e("TAG", "getAllPokemon: ${it.message}")
            }
    }

}

interface MainInput : BaseViewModel.Input {
    fun onLoad()
    fun onNextPage(offset: Int)
}

interface MainOutput : BaseViewModel.Output {
    fun displayPokemonList(pokemonList: List<Pokemon>)
}

interface MainRoute : BaseViewModel.Route {
    fun toDetail()
}