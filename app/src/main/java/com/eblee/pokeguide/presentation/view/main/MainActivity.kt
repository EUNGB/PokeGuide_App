package com.eblee.pokeguide.presentation.view.main

import android.os.Build.VERSION_CODES.P
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eblee.pokeguide.R
import com.eblee.pokeguide.data.utils.PokemonCVSReader.getPokemonNamesByKorean
import com.eblee.pokeguide.databinding.ActivityMainBinding
import com.eblee.pokeguide.domain.entity.Pokemon
import com.eblee.pokeguide.presentation.utils.EndlessRecyclerViewScrollListener
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by inject<MainViewModel>()

    private lateinit var mAdapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        initObserver()
        initUi()
    }

    private fun initAdapter() = with(binding) {
        mAdapter = PokemonListAdapter()
        val gridLayoutManager = GridLayoutManager(this@MainActivity, 2)
        rvPokemon.apply {
            adapter = mAdapter
            layoutManager = gridLayoutManager
            addOnScrollListener(object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    viewModel.input.onNextPage(totalItemsCount + 1)
                }
            })
        }
    }

    private fun initObserver() {
        viewModel.pokemonListLiveData.observe(this, ::displayList)
    }

    private fun initUi() {
        viewModel.input.onLoad()
    }

    private fun displayList(list: List<Pokemon>) {
        mAdapter.setPokemonList(list)
    }

}