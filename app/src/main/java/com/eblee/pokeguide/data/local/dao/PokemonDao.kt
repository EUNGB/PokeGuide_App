package com.eblee.pokeguide.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eblee.pokeguide.data.local.entity.PokemonNameEntity
import io.reactivex.Single

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonName(pokemonNameEntity: PokemonNameEntity)

    @Query("SELECT * FROM pokemonName ORDER BY id LIMIT 20 OFFSET :offset")
    fun getPokemonNames(offset: Int): Single<List<PokemonNameEntity>>

}