package com.eblee.pokeguide.data.respository

import com.eblee.pokeguide.data.local.AppDatabase
import com.eblee.pokeguide.data.local.entity.PokemonNameEntity
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import io.reactivex.Single

class PokemonLocalRepositoryImpl(
    private val db: AppDatabase
) : PokemonLocalRepository {

    override fun insertPokemonKoreanName(pokemonNameEntity: PokemonNameEntity) {
        db.pokeDao.insertPokemonName(pokemonNameEntity)
    }

    override fun getPokemonNameById(id: Int): Single<PokemonNameEntity> {
        return db.pokeDao.getPokemonNameById(id)
    }

}