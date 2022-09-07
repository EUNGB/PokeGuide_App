package com.eblee.pokeguide.data.respository

import com.eblee.pokeguide.data.local.AppDatabase
import com.eblee.pokeguide.data.local.entity.PokemonCatchEntity
import com.eblee.pokeguide.data.local.entity.PokemonNameEntity
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository
import io.reactivex.Completable
import io.reactivex.Single

class PokemonLocalRepositoryImpl(
    private val db: AppDatabase
) : PokemonLocalRepository {

    override fun insertPokemonKoreanName(pokemonNameEntity: PokemonNameEntity) {
        db.pokeDao.insertPokemonName(pokemonNameEntity)
    }

    override fun getPokemonNames(offset: Int): Single<List<PokemonNameEntity>> {
        return db.pokeDao.getPokemonNames(offset)
    }

    override fun insertCatchPokemon(pokemonCatchEntity: PokemonCatchEntity): Completable {
        return db.pokeDao.insertCatchPokemon(pokemonCatchEntity)

    }

    override fun getIsCatch(id: Int): Single<Boolean> {
        return db.pokeDao.isCatchPokemon(id)
            .map { it > 0 }
    }

    override fun removerCatchPokemon(id: Int): Completable {
        return db.pokeDao.removeCatchPokemon(id)
    }
}