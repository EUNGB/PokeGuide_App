package com.eblee.pokeguide.presentation.view.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.use_case.detail.UCGetPokemonInfo
import com.eblee.pokeguide.presentation.base.BaseViewModel

class DetailViewModel(
    private val getPokemonInfo: UCGetPokemonInfo
) : BaseViewModel() {

    val pokemonNameLiveData = MutableLiveData<String>()
    val pokemonImgUrlLiveData = MutableLiveData<String>()
    val pokemonInfoLiveData = MutableLiveData<PokemonInfo>()


    val input = object : DetailInput {
        override fun onLoadPokemon(id: Int) = getPokemonInfoById(id)
    }

    val output = object : DetailOutput {
        override fun displayPokemon(): LiveData<PokemonInfo> = pokemonInfoLiveData
    }

    @SuppressLint("CheckResult")
    private fun getPokemonInfoById(id: Int) {
        getPokemonInfo.invoke(id)
            .subscribe({ info ->
                pokemonInfoLiveData.value = info
            }) {
                it.printStackTrace()
                // TODO 예외처리 output 추가 필요
            }
    }

}

interface DetailInput : BaseViewModel.Input {
    fun onLoadPokemon(id: Int)
}

interface DetailOutput : BaseViewModel.Output {
    fun displayPokemon(): LiveData<PokemonInfo>
}

interface DetailRoute : BaseViewModel.Route {

}