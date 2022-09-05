package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class AbilityEntity(
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int,
    val ability: Ability
)

data class Ability(
    val name: String,
    val url: String
)