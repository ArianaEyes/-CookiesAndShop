package com.ariana.cookiesandshop.pages.MostrarLista

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.layout.ContentScale
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import com.ariana.cookiesandshop.pages.Home.HomeActivity
import com.ariana.cookiesandshop.pages.Home.PostresService
import com.ariana.cookiesandshop.R
import com.ariana.cookiesandshop.models.Postres
import com.ariana.cookiesandshop.pages.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.azulClaro
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.davidchura.proyectothor.components.MyTopAppBar
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MostrarLista : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[ListaViewModel::class.java]

        val api = Retrofit.Builder()
            .baseUrl("https://wyper.alwaysdata.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostresService::class.java)
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {

                val uiState by viewModel.uiState.collectAsState()

                Scaffold(
                    containerColor = Color.Transparent,
                    topBar = { MyTopAppBar("Volver") }) { innerPadding ->

                    var cargando by remember { mutableStateOf(true) }
                    var postres by remember { mutableStateOf<List<Postres>>(emptyList()) }

                    LaunchedEffect(key1 = Unit) {
                        try {
                            postres = api.getPostres()
                            Log.d("POSTRES_SIZE", "Total: ${postres.size}")
                            postres.forEach {
                                Log.d("IMAGEN_URL", it.imagen ?: "NULL")
                            }
                        } catch (e: Exception) {
                            Log.e("ERROR_API", e.message.toString())
                        } finally {
                            cargando = false
                        }
                    }

                    Box(
                        Modifier.padding(innerPadding)
                            .background(azulFondo.copy(.8f))
                    ) {
                        when (val state = uiState) {
                            is ListaUIState.Loading -> {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }

                            is ListaUIState.Error -> {
                                Column(modifier = Modifier.align(Alignment.Center)) {
                                    Text(state.message, color = MaterialTheme.colorScheme.error)
                                    Button(onClick = { viewModel.fetchLista() }) {
                                        Text("Reintentar")
                                    }
                                }
                            }

                            is ListaUIState.Success -> {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp)
                                ) {
                                    items(state.postre, key = { it.id_postre }) { itemPostres ->
                                        FilaPostres(itemPostres)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

        private fun seleccionarPostre(itemPostres: Postres) {
            Toast.makeText(
                this@MostrarLista, itemPostres.nom_postre,
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this, MostrarLista::class.java)
            val bundle = Bundle().apply {
                putInt("Tipo", itemPostres.id_tipo)
                putString("nombre", itemPostres.nom_postre)
                putString("descripcion", itemPostres.descripcion)
            }
            intent.putExtras(bundle)
            startActivity(intent)
        }
}
