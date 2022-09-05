package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class GenerationIvEntity(
    @SerializedName("diamond-pearl")
    val diamondPearl: DiamondPearlEntity,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilverEntity,
    val platinum: PlatinumEntity
)