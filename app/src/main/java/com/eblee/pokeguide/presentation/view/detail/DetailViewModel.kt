package com.eblee.pokeguide.presentation.view.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.use_case.detail.UCCatchPokemon
import com.eblee.pokeguide.domain.use_case.detail.UCGetPokemonInfo
import com.eblee.pokeguide.domain.use_case.detail.UCRemoveCatchPokemon
import com.eblee.pokeguide.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(
    private val getPokemonInfo: UCGetPokemonInfo,
    private val catchPokemon: UCCatchPokemon,
    private val removePokemon: UCRemoveCatchPokemon
) : BaseViewModel() {

    val pokemonNameLiveData = MutableLiveData<String>()
    val pokemonImgUrlLiveData = MutableLiveData<String>()
    val pokemonInfoLiveData = MutableLiveData<PokemonInfo>()
    val isCatch = MutableLiveData<Boolean>(false)

    val input = object : DetailInput {
        override fun onLoadPokemon(id: Int) = getPokemonInfoById(id)
        override fun clickCatch() = catchPokemon()
        override fun clickRemove() = removeCatchPokemon()
    }

    val output = object : DetailOutput {
        override fun displayPokemon(): LiveData<PokemonInfo> = pokemonInfoLiveData
    }

    @SuppressLint("CheckResult")
    private fun getPokemonInfoById(id: Int) {
        getPokemonInfo.invoke(id)
            .subscribe({ info ->
                pokemonInfoLiveData.value = info
                isCatch.value = info.isCatch
            }) {
                it.printStackTrace()
                // TODO 예외처리 output 추가 필요
            }
    }

    @SuppressLint("CheckResult")
    private fun catchPokemon() {
        catchPokemon.invoke(pokemonInfoLiveData.value!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isCatch.value = true
            }) {
                // TODO : 실패 예외처리
                isCatch.value = false
            }
    }

    @SuppressLint("CheckResult")
    private fun removeCatchPokemon() {
        removePokemon.invoke(pokemonInfoLiveData.value!!.pokemonEntity.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isCatch.value = false
            }) {
                // TODO: 실패 예외처리
            }
    }
}

interface DetailInput : BaseViewModel.Input {
    fun onLoadPokemon(id: Int)
    fun clickCatch()
    fun clickRemove()
}

interface DetailOutput : BaseViewModel.Output {
    fun displayPokemon(): LiveData<PokemonInfo>
}

interface DetailRoute : BaseViewModel.Route {

}