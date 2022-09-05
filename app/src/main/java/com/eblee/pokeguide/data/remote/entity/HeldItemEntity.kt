package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class HeldItemEntity(
    val item: ItemEntity,
    @SerializedName("version_details")
    val versionDetails: List<VersionDetailEntity>
)