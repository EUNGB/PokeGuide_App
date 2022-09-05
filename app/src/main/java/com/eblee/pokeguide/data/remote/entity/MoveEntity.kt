package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class MoveEntity(
    val move: Move,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetailEntity>
)

data class Move(
    val name: String,
    val url: String
)