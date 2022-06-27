package com.example.coinsapp.data.remote.dto

data class CoinsListState(
    val isLoading : Boolean = false,
    val coins : List<Coins> = emptyList(),
    val error : String = ""
)