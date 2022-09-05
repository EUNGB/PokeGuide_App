package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class FireredLeafgreenEntity(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_shiny")
    val frontShiny: String
)