package com.eblee.pokeguide.presentation.view.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eblee.pokeguide.databinding.ItemPokemonBinding
import com.eblee.pokeguide.domain.entity.Pokemon

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonItemViewHolder>() {

    private var pokemonList = mutableListOf<Pokemon>()

    class PokemonItemViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            with(binding) {
                tvNum.text = pokemon.getId().toString()
                tvName.text = pokemon.name
                Glide.with(root.context)
                    .load(pokemon.getImageUrl())
                    .into(ivPokemon)
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
    fun setPokemonList(list: List<Pokemon>) {
        pokemonList = list.toMutableList()
        notifyDataSetChanged()
    }

}