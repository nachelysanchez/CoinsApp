package com.example.coinsapp.ui.coins

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinsapp.data.remote.dto.CoinsListState
import com.example.coinsapp.data.repositorios.CoinsRepository
import com.example.coinsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val repository: CoinsRepository
) : ViewModel() {
    private var _state = mutableStateOf(CoinsListState())
    val state: State<CoinsListState> = _state

    init {
        repository.getCoins().onEach {
                result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CoinsListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = CoinsListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinsListState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }
}