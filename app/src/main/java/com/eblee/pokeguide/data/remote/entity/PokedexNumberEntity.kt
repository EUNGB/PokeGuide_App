package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class PokedexNumberEntity(
    @SerializedName("entry_number")
    val entryNumber: Int,
    val pokedex: PokedexEntity
)