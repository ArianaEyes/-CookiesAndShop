package com.ariana.cookiesandshop.pages.mapas.activities

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.ariana.cookiesandshop.pages.mapas.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.pages.detalles.DetallesViewModel
import com.ariana.cookiesandshop.pages.detalles.PostreDetalleUIState
import com.ariana.cookiesandshop.pages.mapas.FilaPostresDelivery
import com.ariana.cookiesandshop.ui.theme.PlusJakarta
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.davidchura.proyectothor.components.TopBar

class EncontrarLocalActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[DetallesViewModel::class.java]
        viewModel.fetchPostres()
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {
                val uiState by viewModel.uiState.collectAsState()
                var imagenUri by remember { mutableStateOf<Uri?>(null) }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { uri: Uri? ->
                    imagenUri = uri
                    viewModel.imagen = uri?.toString() ?: ""
                }
                Scaffold(
                    containerColor = Color.Transparent,
                    topBar = { TopBar("Volver")}) { innerPadding ->

                    Box(
                        Modifier.padding(innerPadding)
                            .fillMaxSize()
                            .background(azulFondo.copy(.8f))
                    ) {

                        when (val state = uiState) {

                            is PostreDetalleUIState.Loading -> {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }

                            is PostreDetalleUIState.Error -> {
                                Column(modifier = Modifier.align(Alignment.Center)) {
                                    Text(state.message, color = MaterialTheme.colorScheme.error)
                                    Button(onClick = { viewModel.fetchPostres() }) {
                                        Text("Reintentar")
                                    }
                                }
                            }

                            is PostreDetalleUIState.SuccessList -> {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    val context = LocalContext.current
                                    Button( onClick = {
                                        val intent = Intent(context, MapaGlobalActivity::class.java)
                                        context.startActivity(intent)
                                    }, modifier = Modifier
                                        .width(140.dp)
                                        .height(35.dp)
                                        .padding(top = 15.dp),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(
                                            "Lugar",
                                            style = TextStyle(
                                                color = Color.Black,
                                                fontSize = 13.sp)
                                            , fontFamily = PlusJakarta, fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top=50.dp, start = 16.dp, end = 16.dp)

                                ) {

                                    items(state.postres, key = { it.id_postre }) { itemPostres ->
                                        Column(Modifier
                                            .clickable {
                                                Log.d("CLICK_DEBUG", "Clic en postre: ${itemPostres.id_postre}")
                                                seleccionarPostre(itemPostres.id_postre)
                                            }){
                                            FilaPostresDelivery(itemPostres, viewModel)
                                        }

                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
    fun seleccionarPostre(id_postre: Int) {
        val intent = Intent(this, DeliveryActivity::class.java)
        val bundle = Bundle().apply {
            putInt("id_postre", id_postre)
        }
        intent.putExtras(bundle)
        startActivity(intent)
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )
    }
}

