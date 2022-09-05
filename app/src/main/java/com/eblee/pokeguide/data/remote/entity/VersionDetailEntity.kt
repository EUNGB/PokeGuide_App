package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class VersionDetailEntity(
    val rarity: Int,
    val version: VersionEntity
)