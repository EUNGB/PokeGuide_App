package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class GenerationIEntity(
    @SerializedName("red-blue")
    val redBlue: RedBlueEntity,
    val yellow: YellowEntity
)