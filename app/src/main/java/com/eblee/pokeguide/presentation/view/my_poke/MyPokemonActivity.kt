package com.eblee.pokeguide.presentation.view.my_poke

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eblee.pokeguide.data.local.entity.PokemonCatchEntity
import com.eblee.pokeguide.presentation.view.my_poke.ui.theme.PokeGuideTheme
import org.koin.android.ext.android.inject

class MyPokemonActivity : ComponentActivity() {

    private val viewModel: MyPokemonViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokeGuideTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val pokemonList by viewModel.pokemonListLiveData.observeAsState(initial = mutableListOf())
                    LazyColumn {
                        itemsIndexed( pokemonList) { index: Int, item: PokemonCatchEntity ->
                            MyPokemonItem(pokemon = item)
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun DefaultPreview(@PreviewParameter(PokemonProvider::class) pokemon: PokemonCatchEntity) {
    MyPokemonItem(pokemon = pokemon)
}

class PokemonProvider: PreviewParameterProvider<PokemonCatchEntity> {
    override val values: Sequence<PokemonCatchEntity> = sequenceOf(PokemonCatchEntity(1, "이상해씨", "", "grass"))
}

@Composable
fun MyPokemonItem(pokemon: PokemonCatchEntity) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .height(150.dp)
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .align(Alignment.BottomCenter)
                .clip(
                    RoundedCornerShape(8.dp)
                )
                .background(Color.Blue)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(0.dp, 0.dp, 0.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${pokemon.id}",
                    color = colorResource(id = com.eblee.pokeguide.R.color.white),
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = pokemon.name,
                    color = colorResource(id = com.eblee.pokeguide.R.color.white),
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Row(
            modifier = Modifier.height(100.dp)
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painter = painterResource(id = com.eblee.pokeguide.R.drawable.ic_pokemon_ball_open),
                contentDescription = "pokemon image"
            )
        }
    }
}