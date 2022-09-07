package com.eblee.pokeguide.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eblee.pokeguide.data.local.dao.PokemonDao
import com.eblee.pokeguide.data.local.entity.PokemonCatchEntity
import com.eblee.pokeguide.data.local.entity.PokemonNameEntity

@Database(
    entities = [PokemonNameEntity::class, PokemonCatchEntity::class],
    version = 2
)
abstract class AppDatabase() : RoomDatabase() {

    abstract val pokeDao: PokemonDao

    companion object {
        const val DATABASE_NAME = "poke_db"
    }

}