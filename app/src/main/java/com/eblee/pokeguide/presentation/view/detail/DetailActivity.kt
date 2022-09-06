package com.eblee.pokeguide.presentation.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.eblee.pokeguide.R
import com.eblee.pokeguide.data.remote.entity.PokemonEntity.Companion.getImgUrl
import com.eblee.pokeguide.data.remote.entity.PokemonSpeciesEntity.Companion.getKoreanName
import com.eblee.pokeguide.data.utils.Constants
import com.eblee.pokeguide.databinding.ActivityDetailBinding
import com.eblee.pokeguide.presentation.view.main.MainActivity.Companion.EXTRA_POKEMON_ID
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        initUi()

        intent.getIntExtra(EXTRA_POKEMON_ID, -1).also {
            if (it != -1) viewModel.input.onLoadPokemon(it)
        }

        viewModel.output.displayPokemon().observe(this) {
            lifecycleScope.launch {
                viewModel.pokemonNameLiveData.value = it.pokemonSpeciesEntity.names.getKoreanName()
                viewModel.pokemonImgUrlLiveData.value = it.pokemonEntity.getImgUrl()
                changeBackgroundColor(it.pokemonEntity.types.first().type.name)
                delay(1500)
                hideShimmer()
            }
        }
    }

    private fun initUi() {
        loadShimmer()
    }

    private fun changeBackgroundColor(type: String) {
        val bgColor = when (type) {
            Constants.TYPE_NORMAL -> binding.root.context.getColor(R.color.normal)
            Constants.TYPE_FIGHTING -> binding.root.context.getColor(R.color.fighting)
            Constants.TYPE_FLYING -> binding.root.context.getColor(R.color.flying)
            Constants.TYPE_POISON -> binding.root.context.getColor(R.color.poison)
            Constants.TYPE_GROUND -> binding.root.context.getColor(R.color.ground)
            Constants.TYPE_GRASS -> binding.root.context.getColor(R.color.grass)
            Constants.TYPE_ROCK -> binding.root.context.getColor(R.color.rock)
            Constants.TYPE_BUG -> binding.root.context.getColor(R.color.bug)
            Constants.TYPE_GHOST -> binding.root.context.getColor(R.color.ghost)
            Constants.TYPE_ELECTRIC -> binding.root.context.getColor(R.color.electric)
            Constants.TYPE_STEEL -> binding.root.context.getColor(R.color.steel)
            Constants.TYPE_FIRE -> binding.root.context.getColor(R.color.fire)
            Constants.TYPE_WATER -> binding.root.context.getColor(R.color.water)
            Constants.TYPE_ICE -> binding.root.context.getColor(R.color.ice)
            Constants.TYPE_DRAGON -> binding.root.context.getColor(R.color.dragon)
            Constants.TYPE_DARK -> binding.root.context.getColor(R.color.dark)
            Constants.TYPE_FAIRY -> binding.root.context.getColor(R.color.fairy)
            Constants.TYPE_UNKNOWN -> binding.root.context.getColor(R.color.unknown)
            Constants.TYPE_SHADOW -> binding.root.context.getColor(R.color.shadow)
            else -> binding.root.context.getColor(R.color.unknown)
        }

        DrawableCompat.setTint(
            DrawableCompat.wrap(binding.clTop.background),
            bgColor
        )
    }

    private fun loadShimmer() {
        binding.skeletonLayout.startShimmer()
    }

    private fun hideShimmer() {
        binding.skeletonLayout.stopShimmer()
        binding.skeletonLayout.isGone = true
        binding.clTop.isVisible = true
        binding.ivPokemon.isVisible = true
    }


}