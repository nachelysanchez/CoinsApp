package com.example.coinsapp.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.coinsapp.data.remote.dto.Coins
import java.text.NumberFormat
import java.util.*

@Composable
fun CoinItem(
    coin : Coins,
    onClick : (Coins) -> Unit
) {

    Column(
        modifier = Modifier
        .padding(8.dp).clickable {onClick(coin)}
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(coin.imageUrl)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = null
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${coin.descripcion}",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${NumberFormatD(coin.valor)}",
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.End,
                color = Color.Green
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
            color = Color.DarkGray
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