package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class VersionsEntity(
    @SerializedName("generation-i")
    val generationI: GenerationIEntity,
    @SerializedName("generation-ii")
    val generationIi: GenerationIiEntity,
    @SerializedName("generation-iii")
    val generationIii: GenerationIiiEntity,
    @SerializedName("generation-iv")
    val generationIv: GenerationIvEntity,
    @SerializedName("generation-v")
    val generationV: GenerationVEntity,
    @SerializedName("generation-vi")
    val generationVi: GenerationViEntity,
    @SerializedName("generation-vii")
    val generationVii: GenerationViiEntity,
    @SerializedName("generation-viii")
    val generationViii: GenerationViiiEntity
)