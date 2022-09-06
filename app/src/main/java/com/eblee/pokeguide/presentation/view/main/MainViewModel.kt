package com.eblee.pokeguide.presentation.view.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.use_case.detail.UCGetPokemonInfo
import com.eblee.pokeguide.domain.use_case.main.UCGetAllPokemon
import com.eblee.pokeguide.domain.use_case.main.UCGetAllPokemonNextPage
import com.eblee.pokeguide.domain.use_case.main.UCSearchPokemon
import com.eblee.pokeguide.presentation.base.BaseViewModel
import com.eblee.pokeguide.presentation.utils.isNumber
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val getAllPokemonUseCase: UCGetAllPokemon,
    private val getAllPokemonNextPage: UCGetAllPokemonNextPage,
    private val searchPokemon: UCSearchPokemon
) : BaseViewModel() {

    val pokemonListLiveData = MutableLiveData<List<PokemonInfo>>()
    private var mPokemonList = mutableListOf<PokemonInfo>()

    val pokemonSearchLiveData = MutableLiveData("")
    val pokemonSearchViewVisible = MutableLiveData(false)

    private val tempSaveListLiveData = MutableLiveData<List<PokemonInfo>>()

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

        override fun onClickSearch() {
            if(!pokemonSearchLiveData.value.isNullOrEmpty()) {
                getSearchPokemon(pokemonSearchLiveData.value!!)
            }
        }
    }

    private val toastMessageLiveData = MutableLiveData<String>()
    val output = object : MainOutput {
        override fun displayPokemonList(pokemonList: List<PokemonInfo>) {
            pokemonListLiveData.value = pokemonList
        }

        override fun showErrorToast(): LiveData<String> = toastMessageLiveData
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
                tempSaveListLiveData.value = mPokemonList
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
                tempSaveListLiveData.value = mPokemonList
                output.displayPokemonList(mPokemonList)
            }) {
                it.printStackTrace()
                Log.e("TAG", "getAllPokemon: ${it.message}")
            }
    }

    @SuppressLint("CheckResult")
    fun getSearchPokemon(query: String) {
        if (query.trim().isNumber()) {
            searchPokemon.invoke(query.trim().toInt())
                .subscribe({
                    output.displayPokemonList(mutableListOf(it))
                }) {
                    toastMessageLiveData.postValue("검색결과가 존재하지 않습니다.")
                }
        } else {
            toastMessageLiveData.postValue("포켓몬 번호를 입력해주세요.")
            // 영문이름만 검색 가능..
            /*searchPokemon.invoke(query.trim())
                .subscribe({
                    mPokemonList = mutableListOf(it)
                    output.displayPokemonList(mPokemonList)
                }) {

                }*/
        }
    }

    fun showSearchView() {
        pokemonSearchViewVisible.postValue(true)
    }

    fun closeSearchView() {
        pokemonSearchViewVisible.postValue(false)
        pokemonSearchLiveData.postValue("")
        output.displayPokemonList(tempSaveListLiveData.value ?: mutableListOf())
    }
}

interface MainInput : BaseViewModel.Input {
    fun onLoad()
    fun onNextPage(offset: Int)
    fun onClickItem(info: PokemonInfo)
    fun onClickSearch()
}

interface MainOutput : BaseViewModel.Output {
    fun displayPokemonList(pokemonList: List<PokemonInfo>)
    fun showErrorToast() : LiveData<String>
}

interface MainRoute : BaseViewModel.Route {
    fun toDetail(): LiveData<PokemonInfo>
}