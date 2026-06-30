package com.ariana.cookiesandshop.pages.mapas.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.ariana.cookiesandshop.models.LugarDetalle
import com.ariana.cookiesandshop.pages.mapas.LugarUIState
import com.ariana.cookiesandshop.pages.mapas.LugarViewModel
import com.ariana.cookiesandshop.pages.mapas.ui.theme.CookiesAndShopTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MapaGlobalActivity : ComponentActivity() {
    private lateinit var viewModel: LugarViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LugarViewModel::class.java]
        viewModel.fetchLugar()  // <- trae TODOS los lugares

        setContent {
            CookiesAndShopTheme {
                val uiState by viewModel.uiState.collectAsState()

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        when (val state = uiState) {
                            is LugarUIState.SuccessList -> {
                                DibujarMapaGlobal(state.lugares)
                            }
                            is LugarUIState.Loading -> { CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))}
                            is LugarUIState.Error -> { Text(state.message) }
                            else -> {}
                        }
                    }

            }
        }
    }
}

@Composable
fun DibujarMapaGlobal(lugares: List<LugarDetalle>) {
    val context = LocalContext.current
    val limaCentro = LatLng(-12.0464, -77.0428) // centro de referencia
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(limaCentro, 11f)
    }

    GoogleMap(modifier = Modifier.fillMaxSize(), cameraPositionState = cameraPositionState) {
        lugares.forEach { lugar ->
            val ubicacion = LatLng(lugar.latitud, lugar.longitud)

            Marker(
                state = MarkerState(position = ubicacion),
                title = lugar.titulo,
                snippet = lugar.subtitulo
            )
            Circle(
                center = ubicacion,
                radius = 200.0,
                fillColor = Color(0x330000FF),
                strokeColor = Color(0x990000FF),
                clickable = true,
                onClick = {
                    val intent = Intent(context, DetalleLugarActivity::class.java)
                    intent.putExtra("id_lugar", lugar.id_lugar)
                    context.startActivity(intent)
                }
            )
        }
    }
}