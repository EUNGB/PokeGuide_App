package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class StatEntity(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: Stat
)

data class Stat(
    val name: String,
    val url: String
)