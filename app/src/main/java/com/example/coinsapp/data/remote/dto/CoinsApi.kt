package com.example.coinsapp.data.remote.dto

import retrofit2.http.GET

interface CoinsApi {
    @GET("/Coins")
    suspend fun getCoins(): List<Coins>

}