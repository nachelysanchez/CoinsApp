package com.example.coinsapp.data.remote.dto

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CoinsApi {
    @GET("/Coins")
    suspend fun getCoins(): List<Coins>

    @POST("/Coins")
    suspend fun postCoin(@Body coins: Coins) : Response<Coins>
}