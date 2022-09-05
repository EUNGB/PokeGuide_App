package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class GenerationVEntity(
    @SerializedName("black-white")
    val blackWhite: BlackWhiteEntity
)