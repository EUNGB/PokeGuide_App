package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class NameEntity(
    val name: String,
    val language: LanguageEntity
)