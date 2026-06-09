package com.ariana.cookiesandshop.pages.MostrarLista

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.ariana.cookiesandshop.pages.detalles.DetallesPostreActivity
import com.ariana.cookiesandshop.pages.detalles.DetallesViewModel
import com.ariana.cookiesandshop.pages.detalles.PostreDetalleUIState
import com.ariana.cookiesandshop.pages.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.azulClaro
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.davidchura.proyectothor.components.TopBar


class MostrarLista : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[DetallesViewModel::class.java]
        viewModel.fetchPostres()
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {


                val uiState by viewModel.uiState.collectAsState()
                var mostrarBottomSheet by remember { mutableStateOf(false) }
                var mostrarActualizar by remember { mutableStateOf(false) }
                var imagenUri by remember { mutableStateOf<Uri?>(null) }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { uri: Uri? ->
                    imagenUri = uri
                    viewModel.imagen = uri?.toString() ?: ""
                }
                Scaffold(
                    containerColor = Color.Transparent,
                    topBar = { TopBar("Volver") },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                mostrarBottomSheet = true
                                mostrarActualizar = false
                            },
                            containerColor = azulClaro
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Nuevo")
                        }
                    }) { innerPadding ->

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
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp)

                                ) {
                                    items(state.postres, key = { it.id_postre }) { itemPostres ->
                                        Column(Modifier
                                            .clickable {
                                                mostrarBottomSheet = true
                                                mostrarActualizar = true
                                                viewModel.id_postre = itemPostres.id_postre.toString()
                                                viewModel.nom_postre = itemPostres.nom_postre
                                                viewModel.id_tipo = itemPostres.id_tipo
                                                viewModel.receta = itemPostres.receta
                                                viewModel.precio=itemPostres.precio.toString()
                                                viewModel.disponible = itemPostres.disponible
                                                viewModel.stock=itemPostres.stock.toString()
                                                viewModel.imagen = itemPostres.imagen
                                                viewModel.descripcion = itemPostres.descripcion
                                                viewModel.calorias = itemPostres.calorias.toString()

                                                seleccionarPostre(itemPostres.id_postre) }){
                                                FilaPostres(itemPostres,viewModel)
                                        }

                                    }
                                }
                            }
                        }
                    }
                    if (mostrarBottomSheet) {
                        ModalBottomSheet(
                            onDismissRequest = {
                                mostrarBottomSheet = false
                            }
                        ) {
                            Column(Modifier.padding(24.dp)
                                .height(580.dp)
                                .verticalScroll(rememberScrollState())) {
                                val etiquetaTitulo = if(mostrarActualizar) "Actualizar director ${viewModel.id_postre}"
                                else "¿Qué postre vamos a vender🍪?"
                                Text(
                                    text = etiquetaTitulo,
                                    style = MaterialTheme.typography.headlineMedium,
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                                OutlinedTextField(
                                    label = { Text("Nombre completo") },
                                    value = viewModel.nom_postre,
                                    onValueChange = { viewModel.nom_postre = it },
                                    modifier = Modifier.fillMaxWidth()
                                )

                                OutlinedTextField(
                                    label = { Text("Receta") },
                                    value = viewModel.receta,
                                    onValueChange = { viewModel.receta = it },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                OutlinedTextField(
                                        label = { Text("Precio") },
                                value = viewModel.precio,
                                onValueChange = { viewModel.precio = it },
                                modifier = Modifier.fillMaxWidth()
                                )
                                OutlinedTextField(
                                    label = { Text("Stock") },
                                    value = viewModel.stock,
                                    onValueChange = { viewModel.stock = it },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                OutlinedTextField(
                                    label = { Text("Imagen") },
                                    value = viewModel.imagen,
                                    readOnly = true,
                                    onValueChange = {
                                        //viewModel.imagen = it
                                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Button(
                                    onClick = {
                                        launcher.launch("image/*")
                                    }
                                ) {
                                    Text("Seleccionar imagen")
                                }
                                OutlinedTextField(
                                    label = { Text("Descripción") },
                                    value = viewModel.descripcion,
                                    onValueChange = { viewModel.descripcion = it },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                OutlinedTextField(
                                    label = { Text("Calorías") },
                                    value = viewModel.calorias,
                                    onValueChange = { viewModel.calorias = it },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                OutlinedButton(onClick = {
                                    mostrarBottomSheet = false
                                    if(mostrarActualizar)
                                        viewModel.updatePostre()
                                    else
                                        viewModel.insertPostre()
                                }, modifier = Modifier.padding(top=10.dp)) {
                                    val etiquetaboton =  if(mostrarActualizar) "Actualizar" else "Guardar"
                                    Text(etiquetaboton)
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    fun seleccionarPostre(id_postre: Int) {
        val intent = Intent(this, DetallesPostreActivity::class.java)
        val bundle = Bundle().apply {
            putInt("id_postre", id_postre)
        }
        intent.putExtras(bundle)
        startActivity(intent)
        overridePendingTransition(
            android.R.anim.fade_in,
                android.R.anim.fade_out
        )
    }
}
