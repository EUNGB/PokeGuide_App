package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class FlavorTextEntryEntity(
    @SerializedName("flavor_text")
    val flavorText: String,
    val language: LanguageEntity,
    val version: VersionEntity
)