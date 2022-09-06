package com.eblee.pokeguide.data.remote.entity


import com.eblee.pokeguide.presentation.utils.getApiId
import com.google.gson.annotations.SerializedName

data class PokemonSpeciesEntity(
    val id: Int,
    val name: String,
    val order: Int,
    @SerializedName("gender_rate")
    val genderRate: Int,
    @SerializedName("capture_rate")
    val captureRate: Int,
    @SerializedName("base_happiness")
    val baseHappiness: Int,
    @SerializedName("is_baby")
    val isBaby: Boolean,
    @SerializedName("is_legendary")
    val isLegendary: Boolean,
    @SerializedName("is_mythical")
    val isMythical: Boolean,
    @SerializedName("hatch_counter")
    val hatchCounter: Int,
    @SerializedName("has_gender_differences")
    val hasGenderDifferences: Boolean,
    @SerializedName("forms_switchable")
    val formsSwitchable: Boolean,
    @SerializedName("growth_rate")
    val growthRate: GrowthRateEntity,
    @SerializedName("pokedex_numbers")
    val pokedexNumbers: List<PokedexNumberEntity>,
    @SerializedName("egg_groups")
    val eggGroups: List<EggGroupEntity>,
    val color: ColorEntity,
    val shape: ShapeEntity,
    @SerializedName("evolves_from_species")
    val evolvesFromSpecies: EvolvesFromSpeciesEntity,
    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChainEntity,
    val habitat: Any,
    val generation: GenerationEntity,
    val names: List<NameEntity>,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryEntity>,
    @SerializedName("form_descriptions")
    val formDescriptions: List<FormDescriptionEntity>,
    val genera: List<GeneraEntity>,
    val varieties: List<VarietyEntity>
) {
    companion object {
        fun List<NameEntity>.getKoreanName(): String {
            return this.firstOrNull() { it.language.url.getApiId() == 3 }?.name ?: ""
        }
    }
}