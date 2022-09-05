package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class DreamWorldEntity(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any
)