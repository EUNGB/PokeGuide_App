package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class GenerationViiEntity(
    val icons: IconsEntity,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoonEntity
)