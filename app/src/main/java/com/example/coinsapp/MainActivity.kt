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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.coinsapp.data.remote.dto.Coins
import com.example.coinsapp.ui.coins.CoinsConsulta
import com.example.coinsapp.ui.coins.CoinsRegistro
import com.example.coinsapp.ui.coins.CoinsViewModel
import com.example.coinsapp.ui.componentes.CoinItem
import com.example.coinsapp.ui.componentes.SplashScreen
import com.example.coinsapp.ui.theme.CoinsAppTheme
import com.example.coinsapp.utils.Screen
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
                    color = MaterialTheme.colors.secondaryVariant
                ) {
                    MyAppCoins()
                }
            }
        }
    }
}

@Composable
fun MyAppCoins(){
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Screen.SplashScreen.route
    ){
        composable(Screen.CoinsConsulta.route){
            CoinsConsulta(navHostController = navHostController)
        }
        composable(Screen.CoinsRegistro.route){
            CoinsRegistro(navHostController = navHostController)
        }
        composable(Screen.SplashScreen.route){
            SplashScreen(navHostController = navHostController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoinsAppTheme {
        MyAppCoins()
    }
}