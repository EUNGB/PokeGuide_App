package com.eblee.pokeguide.domain.entity


// TODO : 저장여부, 캐치여부
data class Pokemon(
    var name: String,
    val url: String,
    var info: PokemonInfo?
) {
    fun getId(): Int {
        return url.split("/".toRegex()).dropLast(1).last().toInt()
    }

    fun getImageUrl(): String {
        val index = getId()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}
