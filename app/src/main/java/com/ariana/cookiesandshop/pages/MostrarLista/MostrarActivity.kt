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
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ariana.cookiesandshop.pages.Home.HomeActivity
import com.ariana.cookiesandshop.pages.Home.PostresService
import com.ariana.cookiesandshop.R
import com.ariana.cookiesandshop.models.Postres
import com.ariana.cookiesandshop.pages.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.azulFondo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MostrarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = Retrofit.Builder()
            .baseUrl("https://wyper.alwaysdata.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostresService::class.java)
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {
                Scaffold(containerColor = Color.Transparent){
                        innerPadding ->

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
                        }finally {
                            cargando = false
                        }
                    }

                    Box(Modifier.padding(innerPadding)){

                        Column(Modifier.fillMaxSize()) {
                            if(cargando) {
                                Box(modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center) {
                                    CircularProgressIndicator()
                                }
                            } else {
                                Spacer(Modifier.height(40.dp))
                                LazyColumn {
                                    items(items = postres){ itemPostres ->
                                        FilaPostres(itemPostres)
                                    }
                                }
                            }
                        }
                        val context = LocalContext.current
                        Box(Modifier
                            .padding(start = 20.dp, top = 0.dp)
                            .size(40.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color.White)
                        ){
                            IconButton(
                                onClick = {
                                    val intent = Intent(context, HomeActivity::class.java)
                                    context.startActivity(intent)
                                },
                                Modifier.padding(start = 10.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.arrow_back_ios_24dp_000000_fill0_wght400_grad0_opsz24),
                                    contentDescription = null,
                                    Modifier.padding()
                                )
                            }
                        }
                    }
                }


            }
        }
    }
}
@Composable
fun Fila(iPostres: Postres) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Imagen
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(iPostres.imagen)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )

            // Info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = iPostres.nom_postre,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A1A)
                    )
                )
                Text(
                    text = iPostres.receta,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color(0xFF888888)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "S/ ${iPostres.precio}",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = azulFondo
                        )
                    )

                    Box(
                        modifier = Modifier
                            .background(
                                if (iPostres.disponible == 1) Color(0xFFE8F5E9) else Color(0xFFFFEBEE),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = if (iPostres.disponible == 1) "✓ Stock: ${iPostres.stock}" else "Agotado",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (iPostres.disponible == 1) Color(0xFF2E7D32) else Color(0xFFC62828)
                            )
                        )
                    }
                }
            }
        }
    }
}