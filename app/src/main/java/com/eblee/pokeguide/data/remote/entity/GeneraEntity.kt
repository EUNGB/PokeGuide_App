package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class GeneraEntity(
    val genus: String,
    val language: LanguageEntity
)