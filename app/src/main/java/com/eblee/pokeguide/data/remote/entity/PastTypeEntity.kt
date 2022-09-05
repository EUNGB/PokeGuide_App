package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class PastTypeEntity(
    val generation: GenerationEntity,
    val types: List<TypeEntity>
)