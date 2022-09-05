package com.eblee.pokeguide.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemonName")
data class PokemonNameEntity(
    @PrimaryKey val id: Int,
    val languageId: Int,
    val name: String,
    val genus: String
) {
    companion object {
        fun Array<String>.toEntity(): PokemonNameEntity {
            return PokemonNameEntity(
                this[0].toInt(),
                this[1].toInt(),
                this[2],
                this[3]
            )
        }
    }
}
