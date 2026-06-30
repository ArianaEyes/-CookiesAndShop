package com.ariana.cookiesandshop.pages.mapas.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.ariana.cookiesandshop.models.LugarDetalle
import com.ariana.cookiesandshop.pages.mapas.LugarUIState
import com.ariana.cookiesandshop.pages.mapas.LugarViewModel
import com.ariana.cookiesandshop.pages.mapas.activities.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.azulFondo

class DetalleLugarActivity : ComponentActivity() {
    private lateinit var viewModel: LugarViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LugarViewModel::class.java]
        val id_lugar = intent.getIntExtra("id_lugar", -1)
        if (id_lugar == -1) { finish(); return }
        viewModel.fetchLugarPorId(id_lugar)

        setContent {
            CookiesAndShopTheme {
                val uiState by viewModel.uiState.collectAsState()

                    Box(
                        Modifier.padding(0.dp)
                            .fillMaxSize()
                            .background(azulFondo.copy(.8f))
                    ){
                        when (val state = uiState) {
                            is LugarUIState.Success -> {
                                DetalleLugarScreen(lugar = state.Lugardetalle)
                            }
                            is LugarUIState.Loading -> { CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) }
                            is LugarUIState.Error -> { Text(state.message) }
                            else -> {}
                        }
                    }

            }
        }
    }
}

@Composable
fun DetalleLugarScreen(lugar: LugarDetalle) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F6FF))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        // Imagen principal
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            shape = RoundedCornerShape(25.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {

            AsyncImage(
                model = lugar.imagen,
                contentDescription = lugar.titulo,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }


        Spacer(modifier = Modifier.height(16.dp))


        // Nombre del lugar
        Text(
            text = lugar.titulo,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4D589C)
        )


        Text(
            text = lugar.subtitulo,
            fontSize = 16.sp,
            color = Color.Gray
        )


        Spacer(modifier = Modifier.height(20.dp))


        // Card del postre
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFCEDAFF)
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "🍰 ${lugar.nom_postre}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3E4B7A)
                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                Text(
                    text = lugar.receta,
                    fontSize = 15.sp
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))


        // Información del producto
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            InfoCard(
                "💰 Precio",
                "S/ ${lugar.precio}"
            )

            InfoCard(
                "🔥 Calorías",
                "${lugar.calorias}"
            )
        }


        Spacer(modifier = Modifier.height(12.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            InfoCard(
                "📦 Stock",
                "${lugar.stock}"
            )

            InfoCard(
                "✨ Estado",
                if(lugar.disponible == 1)
                    "Disponible"
                else
                    "Agotado"
            )
        }


        Spacer(modifier = Modifier.height(20.dp))


        // Ubicación
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "📍 Ubicación",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )


                Text(
                    text =
                        "Latitud: ${lugar.latitud}\nLongitud: ${lugar.longitud}",
                    fontSize = 14.sp
                )
            }
        }
    }
}


@Composable
fun InfoCard(
    titulo: String,
    valor: String
) {

    Card(
        modifier = Modifier
            .width(160.dp)
            .height(90.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                titulo,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Text(
                valor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF625CB3)
            )
        }
    }
}