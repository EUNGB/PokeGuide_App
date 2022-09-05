package com.eblee.pokeguide.domain.use_case

import com.eblee.pokeguide.data.local.entity.PokemonNameEntity.Companion.toEntity
import com.eblee.pokeguide.domain.repository.PokemonLocalRepository

class UCSavePokemonNames(
    private val pokemonLocalRepository: PokemonLocalRepository
) {
    fun invoke(record: Array<String>) {
        pokemonLocalRepository.insertPokemonKoreanName(record.toEntity())
    }
}