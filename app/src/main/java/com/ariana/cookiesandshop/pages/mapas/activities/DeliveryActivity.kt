package com.ariana.cookiesandshop.pages.mapas.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.ariana.cookiesandshop.components.DibujarMapa
import com.ariana.cookiesandshop.pages.mapas.LugarUIState
import com.ariana.cookiesandshop.pages.mapas.LugarViewModel
import com.ariana.cookiesandshop.ui.theme.CookiesAndShopTheme
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient

class DeliveryActivity : ComponentActivity() {
    private lateinit var viewModel: LugarViewModel
    private lateinit var placesClient: PlacesClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LugarViewModel::class.java]
        val id_postre = intent.getIntExtra("id_postre", -1)
        if (id_postre == -1) { finish(); return }
        Log.d("DELIVERY_DEBUG", "ID recibido: $id_postre")

        viewModel.fetchLugarPorPostreId(id_postre)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        Places.initializeWithNewPlacesApiEnabled(
            applicationContext, "AIzaSyDUxQzUseMy9oT87ojFDCOvvvgdr9vs7jg"
        )
        placesClient = Places.createClient(this)
        setContent {
            CookiesAndShopTheme() {
                val uiState by viewModel.uiState.collectAsState()

                when (val state = uiState) {
                    is LugarUIState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is LugarUIState.Error -> {
                        Column(modifier = Modifier,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(state.message, color = MaterialTheme.colorScheme.error)
                            Button(onClick = { viewModel.fetchLugarPorPostreId(id_postre) }) {
                                Text("Reintentar")
                            }
                        }
                    }
                    is LugarUIState.Success -> {
                        DibujarMapa(placesClient, state.Lugardetalle )
                    }
                }

            }
        }
    }

}