package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class TypeEntity(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)