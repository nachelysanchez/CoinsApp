package com.example.coinsapp.utils

sealed class Screen (val route :String){
    object CoinsConsulta : Screen("Consulta")
    object CoinsRegistro : Screen("Registro")
    object SplashScreen : Screen("Splash")
}