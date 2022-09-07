package com.eblee.pokeguide.presentation.view.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eblee.pokeguide.databinding.ActivityMainBinding
import com.eblee.pokeguide.domain.entity.PokemonInfo
import com.eblee.pokeguide.presentation.utils.EndlessRecyclerViewScrollListener
import com.eblee.pokeguide.presentation.view.detail.DetailActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by inject<MainViewModel>()

    private lateinit var mAdapter: PokemonListAdapter

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        initAdapter()
        initObserver()
        initUi()
        initLauncher()
    }

    private fun initAdapter() = with(binding) {
        mAdapter = PokemonListAdapter()
        mAdapter.setOnItemClickListener { position ->
            viewModel.input.onClickItem(mAdapter.getPokemonInfoByPosition(position))
        }

        val gridLayoutManager = GridLayoutManager(this@MainActivity, 2)
        rvPokemon.apply {
            adapter = mAdapter
            layoutManager = gridLayoutManager
            addOnScrollListener(object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    if (totalItemsCount > 1) {
                        viewModel.input.onNextPage(totalItemsCount + 1)
                    }
                }
            })
        }
    }

    private fun initObserver() {
        viewModel.pokemonListLiveData.observe(this, ::displayList)
        viewModel.route.toDetail().observe(this, ::moveDetail)
        viewModel.output.showErrorToast().observe(this, ::showToast)

    }

    private fun initUi() {
        viewModel.input.onLoad()
        loadShimmer()
        binding.btnSearch.setOnClickListener {
            viewModel.input.onClickSearch()
        }
    }

    private fun initLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let {
                    val id = it.getIntExtra("id", -1)
                    if (id != -1) {
                        viewModel.pokemonListLiveData.value!!.find { it.pokemonEntity.id == id }?.let { findPokemon ->
                            findPokemon.isCatch = it.getBooleanExtra("isCatch", false)
                            mAdapter.notifyItemChanged(id - 1)
                        }
                    }
                }
            }
        }
    }

    private fun displayList(list: List<PokemonInfo>) {
        mAdapter.setPokemonList(list)
        hideShimmer()
    }

    private fun moveDetail(info: PokemonInfo) {
        activityResultLauncher.launch(Intent(this, DetailActivity::class.java).apply {
            putExtra(EXTRA_POKEMON_ID, info.pokemonEntity.id)
        })
    }

    private fun loadShimmer() {
        binding.shimmerLayout.startShimmer()
    }

    private fun hideShimmer() {
        lifecycleScope.launch {
            delay(1000)
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.isGone = true
            binding.rvPokemon.isVisible = true
        }
    }

    private fun showToast(message: String) {
        if (message.isEmpty()) return
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_POKEMON_ID = "EXTRA_POKEMON_ID"
    }
}