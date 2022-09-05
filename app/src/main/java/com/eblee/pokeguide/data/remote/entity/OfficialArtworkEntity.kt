package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class OfficialArtworkEntity(
    @SerializedName("front_default")
    val frontDefault: String
)