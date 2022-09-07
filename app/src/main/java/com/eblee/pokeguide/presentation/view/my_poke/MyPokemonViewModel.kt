package com.eblee.pokeguide.presentation.view.my_poke

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.eblee.pokeguide.data.local.entity.PokemonCatchEntity
import com.eblee.pokeguide.domain.use_case.my_poke.UCGetAllCatchPokemon
import com.eblee.pokeguide.presentation.base.BaseViewModel

class MyPokemonViewModel(
    private val getAllCatchPokemon: UCGetAllCatchPokemon
) : BaseViewModel() {

    val pokemonListLiveData = MutableLiveData<List<PokemonCatchEntity>>()

    init {
        getAllPokemon()
    }

    @SuppressLint("CheckResult")
    fun getAllPokemon() {
        getAllCatchPokemon.invoke()
            .subscribe { list ->
                pokemonListLiveData.value = list
            }
    }

}