package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class GenerationIiiEntity(
    val emerald: EmeraldEntity,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreenEntity,
    @SerializedName("ruby-sapphire")
    val rubySapphire: RubySapphireEntity
)