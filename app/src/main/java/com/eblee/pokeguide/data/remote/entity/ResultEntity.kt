package com.eblee.pokeguide.data.remote.entity


import com.eblee.pokeguide.domain.entity.Pokemon
import com.google.gson.annotations.SerializedName

data class ResultEntity(
    val name: String,
    val url: String
) {
    companion object {
        fun ResultEntity.toMap(): Pokemon {
            return Pokemon(
                this.name,
                this.url,
                null
            )
        }
    }
}