package com.eblee.pokeguide.presentation.view.main

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eblee.pokeguide.R
import com.eblee.pokeguide.data.remote.entity.PokemonEntity.Companion.getImgUrl
import com.eblee.pokeguide.data.utils.Constants.TYPE_BUG
import com.eblee.pokeguide.data.utils.Constants.TYPE_DARK
import com.eblee.pokeguide.data.utils.Constants.TYPE_DRAGON
import com.eblee.pokeguide.data.utils.Constants.TYPE_ELECTRIC
import com.eblee.pokeguide.data.utils.Constants.TYPE_FAIRY
import com.eblee.pokeguide.data.utils.Constants.TYPE_FIGHTING
import com.eblee.pokeguide.data.utils.Constants.TYPE_FIRE
import com.eblee.pokeguide.data.utils.Constants.TYPE_FLYING
import com.eblee.pokeguide.data.utils.Constants.TYPE_GHOST
import com.eblee.pokeguide.data.utils.Constants.TYPE_GRASS
import com.eblee.pokeguide.data.utils.Constants.TYPE_GROUND
import com.eblee.pokeguide.data.utils.Constants.TYPE_ICE
import com.eblee.pokeguide.data.utils.Constants.TYPE_NORMAL
import com.eblee.pokeguide.data.utils.Constants.TYPE_POISON
import com.eblee.pokeguide.data.utils.Constants.TYPE_ROCK
import com.eblee.pokeguide.data.utils.Constants.TYPE_SHADOW
import com.eblee.pokeguide.data.utils.Constants.TYPE_STEEL
import com.eblee.pokeguide.data.utils.Constants.TYPE_UNKNOWN
import com.eblee.pokeguide.data.utils.Constants.TYPE_WATER
import com.eblee.pokeguide.databinding.ItemPokemonBinding
import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.presentation.utils.getApiId

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonItemViewHolder>() {

    private var pokemonList = mutableListOf<PokemonInfo>()

    class PokemonItemViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonInfo) {
            with(binding) {
                tvNum.text = pokemon.pokemonEntity.id.toString()
                tvName.text = pokemon.pokemonSpeciesEntity.names.first { it.language.url.getApiId() == 3 }.name
                Glide.with(root.context)
                    .load(pokemon.pokemonEntity.getImgUrl())
                    .into(ivPokemon)

                val bgColor = when (pokemon.pokemonEntity.types.first().type.name) {
                    TYPE_NORMAL -> binding.root.context.getColor(R.color.normal)
                    TYPE_FIGHTING -> binding.root.context.getColor(R.color.fighting)
                    TYPE_FLYING -> binding.root.context.getColor(R.color.flying)
                    TYPE_POISON -> binding.root.context.getColor(R.color.poison)
                    TYPE_GROUND -> binding.root.context.getColor(R.color.ground)
                    TYPE_GRASS -> binding.root.context.getColor(R.color.grass)
                    TYPE_ROCK -> binding.root.context.getColor(R.color.rock)
                    TYPE_BUG -> binding.root.context.getColor(R.color.bug)
                    TYPE_GHOST -> binding.root.context.getColor(R.color.ghost)
                    TYPE_ELECTRIC -> binding.root.context.getColor(R.color.electric)
                    TYPE_STEEL -> binding.root.context.getColor(R.color.steel)
                    TYPE_FIRE -> binding.root.context.getColor(R.color.fire)
                    TYPE_WATER -> binding.root.context.getColor(R.color.water)
                    TYPE_ICE -> binding.root.context.getColor(R.color.ice)
                    TYPE_DRAGON -> binding.root.context.getColor(R.color.dragon)
                    TYPE_DARK -> binding.root.context.getColor(R.color.dark)
                    TYPE_FAIRY -> binding.root.context.getColor(R.color.fairy)
                    TYPE_UNKNOWN -> binding.root.context.getColor(R.color.unknown)
                    TYPE_SHADOW -> binding.root.context.getColor(R.color.shadow)
                    else -> binding.root.context.getColor(R.color.unknown)
                }

                DrawableCompat.setTint(
                    DrawableCompat.wrap(clCard.background),
                    bgColor
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListAdapter.PokemonItemViewHolder {
        return PokemonListAdapter.PokemonItemViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
        pokemonList[position].let {
            holder.bind(it)
        }

    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPokemonList(list: List<PokemonInfo>) {
        pokemonList = list.toMutableList()
        notifyDataSetChanged()
    }

}