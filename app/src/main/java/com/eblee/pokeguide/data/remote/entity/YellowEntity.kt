package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class YellowEntity(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_gray")
    val backGray: String,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_gray")
    val frontGray: String
)