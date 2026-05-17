package com.ariana.cookiesandshop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ariana.cookiesandshop.models.Postres
import com.ariana.cookiesandshop.pages.MostrarActivity
import com.ariana.cookiesandshop.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.azulClaro
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.ariana.cookiesandshop.ui.theme.chocolate
import com.ariana.cookiesandshop.ui.theme.fondoColor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PostresService {
    @GET("postres.php")
    suspend fun getPostres(): List<Postres>
}
class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
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
                Scaffold(containerColor = Color.Transparent,bottomBar = { BarraIcon(selectedItem = 0) }) { innerPadding ->
                    var postres by remember { mutableStateOf<List<Postres>>(emptyList()) }
                    val context = LocalContext.current
                    LaunchedEffect(key1 = Unit) {
                        try {
                            postres = api.getPostres()
                            Log.d("POSTRES_SIZE", "Total: ${postres.size}")
                            postres.forEach {
                                Log.d("IMAGEN_URL", it.imagen ?: "NULL")
                            }
                        } catch (e: Exception) {
                            Log.e("ERROR_API", e.message.toString())
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .padding(innerPadding)
                            .background(fondoColor)
                            .statusBarsPadding()
                            .verticalScroll(rememberScrollState()),

                        )
                    {

                        Column() {
                            Column(
                                modifier = Modifier.padding(start = 15.dp),
                                horizontalAlignment = Alignment.Start
                            ) {

                                Text(
                                    "CUALQUIER COMIDA!",
                                    modifier = Modifier
                                        .padding(8.dp),
                                    style = TextStyle(
                                        fontWeight = FontWeight(600),
                                        fontSize = 20.sp
                                    )
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(20.dp)
                            ) {

                                Row(
                                    Modifier
                                        .width(360.dp)
                                        .height(60.dp)
                                        .background(Color.White, shape = RoundedCornerShape(50.dp))
                                        .padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 15.dp,
                                            end = 15.dp
                                        ),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painterResource(R.drawable.motorcycle_24dp_000d70_fill0_wght400_grad0_opsz24),
                                        contentDescription = null,
                                        tint = Color.Unspecified
                                    )
                                    Text("Tu pedido está en camino...")

                                    val context = LocalContext.current
                                    IconButton(
                                        onClick = {
                                            val intent = Intent(context, DeliveryActivity::class.java)
                                            context.startActivity(intent)
                                        },
                                        Modifier.padding(start = 30.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.baseline_arrow_forward_ios_24),
                                            contentDescription = null,
                                            Modifier.padding()
                                        )
                                    }
                                }
                                Row(
                                    Modifier
                                        .width(380.dp)
                                        .height(140.dp)
                                        .background(azulFondo, shape = RoundedCornerShape(20.dp))
                                        .padding(
                                            top = 20.dp,
                                            start = 30.dp,
                                            bottom = 0.dp,
                                            end = 20.dp
                                        ),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column()
                                    {
                                        Text(
                                            "Escanea el QR!",
                                            style = TextStyle(color = Color.White, fontSize = 22.sp)
                                        )
                                        Text("Obtén ya tu plato", color = Color.White)
                                        Text("Personaliza tus sabores", color = Color.White)
                                        Button(
                                            onClick = {val intent = Intent(context, MostrarActivity::class.java)
                                                startActivity(intent)}, colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.White
                                            ),
                                            modifier = Modifier
                                                .width(140.dp)
                                                .height(35.dp)
                                                .padding(top = 8.dp),
                                            contentPadding = PaddingValues(0.dp)
                                        ) {
                                            Text(
                                                "Más información",
                                                style = TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight(700)
                                                )
                                            )
                                        }
                                    }
                                    Image(
                                        painter = painterResource(R.drawable.qr),
                                        contentDescription = "Qr", Modifier
                                            .width(100.dp)
                                            .clip(RoundedCornerShape(15.dp))
                                    )
                                }
                                Image(
                                    painterResource(R.drawable.postre3),
                                    contentDescription = "Postrechito",
                                    Modifier
                                        .clip(RoundedCornerShape(20))
                                        .size(width = 380.dp, height = 150.dp),
                                    contentScale = ContentScale.Crop
                                )
                                //Poner encima de esta imagen una oferta(u algo asi ._.)

                                Row(
                                    modifier = Modifier
                                        .width(380.dp)
                                        .height(140.dp)
                                        .horizontalScroll(rememberScrollState())
                                        .clipToBounds(),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    postres.chunked(2).forEach { par ->
                                        par.forEach { postre ->
                                            Box() {
                                                AsyncImage(
                                                    model = ImageRequest.Builder(LocalContext.current)
                                                        .data(postre.imagen)
                                                        .crossfade(true)
                                                        .build(),
                                                    contentDescription = "null",
                                                    Modifier
                                                        .clip(RoundedCornerShape(20.dp))
                                                        .border(
                                                            3.dp,
                                                            chocolate,
                                                            RoundedCornerShape(20.dp)
                                                        )
                                                        .size(width = 120.dp, height = 140.dp),
                                                    contentScale = ContentScale.Crop,
                                                     placeholder = painterResource(R.drawable.nofoto),
                                                    error= painterResource(R.drawable.nofoto)
                                                )
                                                Text(
                                                    postre.nom_postre,
                                                    Modifier.padding(15.dp, top = 95.dp),
                                                    style = TextStyle(
                                                        Color.White,
                                                        fontWeight = FontWeight(900),
                                                        fontSize = 10.sp
                                                    )
                                                )
                                                Text(
                                                    postre.precio.toString(), Modifier.padding(15.dp, top = 110.dp),
                                                    style = TextStyle(
                                                        Color.White,
                                                        fontWeight = FontWeight(900),
                                                        fontSize = 15.sp
                                                    )
                                                )
                                            }
                                        }


                                    }

                                }

                                // AQUÍ EMPIEZA LA SECCIÓN DE POSTRES
                                Column(
                                    Modifier.padding(top = 20.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    Text(
                                        "Postres:",
                                        Modifier.align(Alignment.Start).padding(start = 10.dp, bottom = 20.dp),
                                        style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight(600))
                                    )

                                    postres.chunked(2).forEach { par ->
                                        Row(
                                            Modifier.height(260.dp).width(360.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            par.forEach { postre ->
                                                Column(
                                                    Modifier
                                                        .width(170.dp)
                                                        .height(260.dp)
                                                        .background(Color.White, shape = RoundedCornerShape(20.dp)),
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    Box {
                                                        AsyncImage(
                                                            model = ImageRequest.Builder(LocalContext.current)
                                                                .data(postre.imagen)
                                                                .crossfade(true)
                                                                .build(),
                                                            contentDescription = null,
                                                            modifier = Modifier
                                                                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                                                                .size(width = 170.dp, height = 140.dp),
                                                            contentScale = ContentScale.Crop
                                                        )
                                                        Box(
                                                            Modifier
                                                                .padding(end = 10.dp, top = 10.dp)
                                                                .size(40.dp)
                                                                .clip(RoundedCornerShape(50.dp))
                                                                .background(Color.White)
                                                                .border(1.dp, azulFondo, RoundedCornerShape(50.dp))
                                                                .align(Alignment.TopEnd)
                                                        ) {
                                                            Icon(
                                                                painterResource(R.drawable.favorite_24dp_ffffff_fill0_wght400_grad0_opsz24),
                                                                contentDescription = null,
                                                                Modifier.align(Alignment.Center),
                                                                tint = azulFondo
                                                            )
                                                        }
                                                    }
                                                    Text(
                                                        "S/ ${postre.precio}",
                                                        Modifier.align(Alignment.Start).padding(start = 15.dp, top = 10.dp),
                                                        style = TextStyle(fontSize = 22.sp)
                                                    )
                                                    Text(
                                                        postre.nom_postre,
                                                        Modifier.align(Alignment.Start).padding(start = 15.dp)
                                                    )
                                                    Row {
                                                        Button(
                                                            onClick = {},
                                                            colors = ButtonDefaults.buttonColors(containerColor = azulClaro),
                                                            modifier = Modifier.width(120.dp).height(35.dp).padding(top = 8.dp),
                                                            contentPadding = PaddingValues(0.dp)
                                                        ) {
                                                            Text(
                                                                "Más información",
                                                                style = TextStyle(color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight(700))
                                                            )
                                                        }
                                                        Icon(
                                                            painterResource(R.drawable.outline_add_24),
                                                            contentDescription = null,
                                                            Modifier.padding(top = 10.dp, start = 5.dp)
                                                        )
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
                }
            }
        }
    }


