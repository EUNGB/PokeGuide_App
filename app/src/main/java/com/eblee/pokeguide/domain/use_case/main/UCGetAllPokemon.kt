package com.eblee.pokeguide.domain.use_case.main

import android.annotation.SuppressLint
import android.util.Log
import com.eblee.pokeguide.data.remote.entity.PokemonEntity
import com.eblee.pokeguide.domain.entity.Pokemon
import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import com.eblee.pokeguide.domain.repository.PokemonRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList


class UCGetAllPokemon(
    private val pokemonRepository: PokemonRepository,
    private val pokemonLocalRepository: PokemonLocalRepository
) {

    @SuppressLint("CheckResult")
    fun invoke(): Single<List<Pokemon>> {
      /*  val request: ArrayList<Single<PokemonInfo>> = ArrayList()
        for (id in 1..20) {
            request.add(pokemonRepository.getPokemonInfo(id).subscribeOn(Schedulers.io()))
        }
        Single.zip(request) { it ->
            it.toList() as List<PokemonInfo>
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TAG", "invoke: ${it.toString()}")
            }) {
                it.printStackTrace()
            }*/

        return Single.zip(
            pokemonRepository.getAllPokemon().subscribeOn(Schedulers.io()),
            pokemonLocalRepository.getPokemonNames().subscribeOn(Schedulers.io()),
        ) { response, names ->
            response.results.mapIndexed { index, resultEntity ->
                Pokemon(
                    names[index].name,
                    resultEntity.url,
                    null
                )
            }
        }

//        return pokemonRepository.getAllPokemon()
//            .map { it.results.map { res -> res.toMap() } }
    }

}