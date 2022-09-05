package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class EmeraldEntity(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_shiny")
    val frontShiny: String
)