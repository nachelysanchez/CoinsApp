package com.example.coinsapp.data.repositorios

import com.example.coinsapp.data.remote.dto.Coins
import com.example.coinsapp.data.remote.dto.CoinsApi
import com.example.coinsapp.di.AppModule
import com.example.coinsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Callback
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val api: CoinsApi
){

    fun getCoins(): Flow<Resource<List<Coins>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val coins = api.getCoins() //descarga las monedas de internet, se supone que demora algo

            emit(Resource.Success(coins)) //indicar que se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun postCoin(
        coin: Coins
    ) : String
    {
        var message : String
        var call = api.postCoin(coin)
        var coin = call.body()

        if(call.isSuccessful){
            message = "The coin had saved successfully..."
        }
        else{
            message = "Sorry. Error"
        }
        return message;
        /*withContext(Dispatchers.Main){
            try{
                //TODO: EVALUAR DONDE ES QUE SE PIERDE EL COIN
                val coinResponse = api.postCoin(coin)
            }
            catch(e : Exception){
                val message = e.message
            }
        }*/
    }
}