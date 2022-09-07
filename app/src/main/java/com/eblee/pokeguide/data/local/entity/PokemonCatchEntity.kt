package com.eblee.pokeguide.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_catch")
data class PokemonCatchEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val url: String,
    val type: String
)
