package com.eblee.pokeguide.domain.entity

data class PokemonColor(
    val id: Int,
    val color: Colors
) {
    enum class Colors {
        BLACK, BLUE, BROWN, GRAY, GREEN, PINK, PURPLE, RED, WHITE, YELLOW
    }
}
