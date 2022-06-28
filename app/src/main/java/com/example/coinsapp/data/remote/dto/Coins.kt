package com.example.coinsapp.data.remote.dto

data class Coins(
    val monedaId : Int = 0,
    val descripcion : String? = "",
    val valor : Double? = 0.0,
    val imageUrl : String? = ""

)