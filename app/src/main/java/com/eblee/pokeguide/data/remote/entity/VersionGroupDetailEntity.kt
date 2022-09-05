package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class VersionGroupDetailEntity(
    @SerializedName("level_learned_at")
    val levelLearnedAt: Int,
    @SerializedName("version_group")
    val versionGroup: VersionGroupEntity,
    @SerializedName("move_learn_method")
    val moveLearnMethod: MoveLearnMethodEntity
)