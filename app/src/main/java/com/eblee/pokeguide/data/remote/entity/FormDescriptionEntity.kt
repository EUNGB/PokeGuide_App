package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class FormDescriptionEntity(
    val description: String,
    val language: LanguageEntity
)