package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<ResultEntity>
)