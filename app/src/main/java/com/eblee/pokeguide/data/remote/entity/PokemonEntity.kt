package com.eblee.pokeguide.data.remote.entity


import com.google.gson.annotations.SerializedName

data class PokemonEntity(
    val id: Int,
    var name: String,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val height: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val abilities: List<AbilityEntity>,
    val forms: List<FormEntity>,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndiceEntity>,
    @SerializedName("held_items")
    val heldItems: List<HeldItemEntity>,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<MoveEntity>,
    val species: SpeciesEntity,
    val sprites: SpritesEntity,
    val stats: List<StatEntity>,
    val types: List<TypeEntity>,
    @SerializedName("past_types")
    val pastTypes: List<PastTypeEntity>
) {
    companion object {
        fun PokemonEntity.getImgUrl(): String =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}