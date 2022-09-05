package com.eblee.pokeguide.presentation.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.eblee.pokeguide.R
import com.eblee.pokeguide.data.utils.PokemonCVSReader.getPokemonNamesByKorean
import com.eblee.pokeguide.databinding.ActivitySplashBinding
import com.eblee.pokeguide.presentation.view.main.MainActivity
import com.opencsv.CSVReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import java.io.BufferedReader
import java.io.InputStreamReader

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by inject<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firstLoadNameData()
    }

    private fun firstLoadNameData() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.progressbar.isIndeterminate = true
            binding.tvLoading.isVisible = true
            withContext(Dispatchers.IO) {
                saveKoreanNames()
            }
            moveMain()
        }
    }

    private fun saveKoreanNames() {
        try {
            val inputStreamReader = InputStreamReader(this.resources.openRawResource(R.raw.names))
            val reader = BufferedReader(inputStreamReader)
            val read = CSVReader(reader)
            var record: Array<String>
            while (read.readNext().also { record = it } != null) {
                if (record[1] == "3") {
                    viewModel.saveName(record)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun moveMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }




}