package com.example.coinsapp.ui.coins

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.twotone.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.coinsapp.data.remote.dto.Coins
import com.example.coinsapp.ui.componentes.ValidacionText
import com.example.coinsapp.utils.Screen

@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CoinsRegistro(
    navHostController: NavHostController,
    viewModel: CoinsViewModel = hiltViewModel()
) {
    var nombreError by remember {
        mutableStateOf(false)
    }
    var precioError by remember {
        mutableStateOf(false)
    }
    var imagenError by remember {
        mutableStateOf(false)
    }



    val contexto = LocalContext.current

    Scaffold(
        topBar= {
            TopAppBar(
                title = {
                    Text(
                        text = "Registro de Monedas",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                backgroundColor = Color.DarkGray,
                actions = {
                    IconButton(
                        onClick = { navHostController.navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    nombreError = viewModel.nombre.isBlank()
                    precioError = viewModel.precio.isBlank()
                    imagenError = viewModel.imagen.isBlank()

                    if(!nombreError && !precioError){
                        if(viewModel.precio.toDouble() > 0){
                            //TODO: LLENA CLASES
                            viewModel.Post()
                            Toast.makeText(contexto, "La moneda se guardó correctamente", Toast.LENGTH_LONG).show()
                            navHostController.navigate(Screen.CoinsConsulta.route)
                        }else{
                            Toast.makeText(contexto, "El precio no puede ser menor o igual a cero", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
            {
                Icon(imageVector = Icons.TwoTone.Check, contentDescription = null)
            }
        }

    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            OutlinedTextField(
                value = viewModel.nombre,
                onValueChange = {viewModel.nombre = it},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Nombre de la moneda"
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null
                    )
                },
                isError = nombreError,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text
                )

            )
            ValidacionText(
                estado = nombreError
            )

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            OutlinedTextField(
                value = viewModel.precio,
                onValueChange = {viewModel.precio = it},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Precio"
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                },
                isError = precioError,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Number
                )
            )

            ValidacionText(
                estado = precioError
            )
            Spacer(
                modifier = Modifier.height(40.dp)
            )

            OutlinedTextField(
                value = viewModel.imagen,
                onValueChange = {viewModel.imagen = it},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Imagen URL"
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Text
                )
            )

            /*ValidacionText(
                estado = imagenError
            )*/
            Spacer(
                modifier = Modifier.height(40.dp)
            )
        }
    }
}
/*
fun LlenaClase(
    id: Int,
    descripcion : String,
    valor : Double,
    imagenUrl : String
) : Coins {

    return Coins(
        monedaId = 0,
        descripcion = descripcion,
        valor = valor.toDouble(),
        imageUrl = imagenUrl
    )
}*/
