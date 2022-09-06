package com.eblee.pokeguide.presentation.view.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.use_case.detail.UCGetPokemonInfo
import com.eblee.pokeguide.domain.use_case.main.UCGetAllPokemon
import com.eblee.pokeguide.domain.use_case.main.UCGetAllPokemonNextPage
import com.eblee.pokeguide.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val getAllPokemonUseCase: UCGetAllPokemon,
    private val getAllPokemonNextPage: UCGetAllPokemonNextPage,
    private val getPokemonInfo: UCGetPokemonInfo
) : BaseViewModel() {

    val pokemonListLiveData = MutableLiveData<List<PokemonInfo>>()
    private var mPokemonList = mutableListOf<PokemonInfo>()

//    val progressbarVisibility = MutableLiveData(View.GONE)

    private val toDetail = MutableLiveData<PokemonInfo>()

    val input = object : MainInput {
        override fun onLoad() {
            return getAllPokemon()
        }

        override fun onNextPage(offset: Int) {
            return getAllPokemonNextPage(offset)
        }

        override fun onClickItem(info: PokemonInfo) {
            toDetail.value = info
        }
    }

    val output = object : MainOutput {
        override fun displayPokemonList(pokemonList: List<PokemonInfo>) {
            pokemonListLiveData.value = pokemonList
        }
    }

    val route = object : MainRoute {
        override fun toDetail(): LiveData<PokemonInfo> = toDetail
    }

    @SuppressLint("CheckResult")
    fun getAllPokemon() {
        getAllPokemonUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
    fun onClickItem(info: PokemonInfo)
}

interface MainOutput : BaseViewModel.Output {
    fun displayPokemonList(pokemonList: List<PokemonInfo>)
}

interface MainRoute : BaseViewModel.Route {
    fun toDetail(): LiveData<PokemonInfo>
}