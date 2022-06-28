package com.example.coinsapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.coinsapp.data.remote.dto.Coins
import com.example.coinsapp.ui.coins.CoinsViewModel
import com.example.coinsapp.ui.theme.CoinsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyAppCoins()
                }
            }
        }
    }
}

@Composable
fun MyAppCoins(
    viewModel: CoinsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()){

            items(state.coins){coin ->
                CoinItem(
                    coin = coin,
                    onClick = {}
                )
            }
        }

        if (state.isLoading)
            CircularProgressIndicator()


    }
}

@Composable
fun CoinItem(
    coin : Coins,
    onClick : (Coins) -> Unit
) {

    Column(modifier = Modifier
        .padding(8.dp))
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .padding(2.dp).clickable { onClick(coin)},
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(coin.imageUrl)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = null
            )

            Text(
                text = "${NumberFormatD(coin.valor)}",
                fontStyle = Italic,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.End,
                color = androidx.compose.ui.graphics.Color.Green
            )

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "${coin.descripcion}",
                style = MaterialTheme.typography.body1,
                fontWeight = Bold
            )
        }

        Spacer(
            modifier = Modifier
                .height(3.dp)
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .width(2.dp),
            color = androidx.compose.ui.graphics.Color.DarkGray
        )

    }

}


fun NumberFormatD(
    number : Double?
) : String{
    val country : String = "US";
    val language : String = "en";
    val str : String = NumberFormat.getCurrencyInstance(Locale(language, country)).format(number);
    return str;
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoinsAppTheme {
        MyAppCoins()
    }
}