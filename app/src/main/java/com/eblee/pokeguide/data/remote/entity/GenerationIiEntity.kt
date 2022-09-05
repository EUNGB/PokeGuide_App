package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class GenerationIiEntity(
    val crystal: CrystalEntity,
    val gold: GoldEntity,
    val silver: SilverEntity
)