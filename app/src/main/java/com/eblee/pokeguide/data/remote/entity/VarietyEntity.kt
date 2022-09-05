package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class VarietyEntity(
    @SerializedName("is_default")
    val isDefault: Boolean,
    val pokemon: PokemonEntity
)