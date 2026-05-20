package com.ariana.cookiesandshop.pages.Detalles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.ariana.cookiesandshop.components.BarraIcon
import com.ariana.cookiesandshop.ui.theme.CookiesAndShopTheme

class DetallesPostreActivity : ComponentActivity() {
    private lateinit var viewModel: DetallesViewModel // 👈 aquí

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[DetallesViewModel::class.java] // 👈 sin "val"
        val id_postre = intent.getIntExtra("id_postre", -1)
        if (id_postre == -1) { finish(); return }
        android.util.Log.d("DETALLES", "id recibido: $id_postre")

        viewModel.fetchPostre(id_postre)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {
                val uiState by viewModel.uiState.collectAsState()
                Scaffold(
                    containerColor = Color.Transparent,
                    bottomBar = { BarraIcon(selectedItem = 2) }) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            when (val state = uiState) {
                                is PostreDetalleUIState.Loading -> {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                }
                                is PostreDetalleUIState.Error -> {
                                    Column(modifier = Modifier.align(Alignment.Center)) {
                                        Text(state.message, color = MaterialTheme.colorScheme.error)
                                        Button(onClick = { viewModel.fetchPostre(id_postre) }) {
                                            Text("Reintentar")
                                        }
                                    }
                                }
                                is PostreDetalleUIState.Success -> {
                                    PostreDetalle(state.DetallesPostre)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: android.content.Intent) { // 👈 agrega esto
        super.onNewIntent(intent)
        setIntent(intent)
        val id_postre = intent.getIntExtra("id_postre", -1)
        android.util.Log.d("DETALLES", "onNewIntent id: $id_postre")
        if (id_postre != -1) viewModel.fetchPostre(id_postre)
    }
}