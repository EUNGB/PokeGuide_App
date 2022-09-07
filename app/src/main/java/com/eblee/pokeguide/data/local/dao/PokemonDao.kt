package com.eblee.pokeguide.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eblee.pokeguide.data.local.entity.PokemonCatchEntity
import com.eblee.pokeguide.data.local.entity.PokemonNameEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonName(pokemonNameEntity: PokemonNameEntity)

    @Query("SELECT * FROM pokemonName ORDER BY id LIMIT 20 OFFSET :offset")
    fun getPokemonNames(offset: Int): Single<List<PokemonNameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatchPokemon(pokemon: PokemonCatchEntity): Completable

    @Query("SELECT COUNT(*) FROM pokemon_catch WHERE id=:id ORDER BY id ")
    fun isCatchPokemon(id: Int): Single<Int>

    @Query("DELETE FROM POKEMON_CATCH WHERE id=:id")
    fun removeCatchPokemon(id: Int): Completable
}