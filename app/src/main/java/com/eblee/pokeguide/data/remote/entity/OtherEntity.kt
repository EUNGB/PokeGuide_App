package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class OtherEntity(
    @SerializedName("dream_world")
    val dreamWorld: DreamWorldEntity,
    val home: HomeEntity,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkEntity
)