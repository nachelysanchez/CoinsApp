package com.example.coinsapp.data.remote.dto

data class Coins(
    val monedaId : Integer,
    val descripcion : String? = "",
    val valor : Double? = 0.0,
    val imageUrl : String? = ""

)