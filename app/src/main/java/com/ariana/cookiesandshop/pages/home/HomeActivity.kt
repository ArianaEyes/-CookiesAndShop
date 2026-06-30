package com.ariana.cookiesandshop.pages.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import coil.compose.AsyncImage
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.ariana.cookiesandshop.R
import com.ariana.cookiesandshop.components.BarraIcon
import com.ariana.cookiesandshop.models.Postres
import com.ariana.cookiesandshop.pages.detalles.DetallesPostreActivity
import com.ariana.cookiesandshop.pages.MostrarLista.MostrarLista
import com.ariana.cookiesandshop.pages.mapas.activities.EncontrarLocalActivity
import com.ariana.cookiesandshop.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.Fjalla
import com.ariana.cookiesandshop.ui.theme.Nunito
import com.ariana.cookiesandshop.ui.theme.PlusJakarta
import com.ariana.cookiesandshop.ui.theme.azulClaro
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.ariana.cookiesandshop.ui.theme.chocolate
import com.ariana.cookiesandshop.ui.theme.fondoColor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlin.jvm.java

interface PostresService {
    @GET("postres.php")
    suspend fun getPostres(): List<Postres>
}
class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = Retrofit.Builder()
            .baseUrl("https://arianini.alwaysdata.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostresService::class.java)
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {
                var isVisible by remember {mutableStateOf(true)}

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
                    var pulse by remember { mutableStateOf(false) }
                    val scope = rememberCoroutineScope()

                    val scale by animateFloatAsState(
                        targetValue = if (pulse) 2f else 1f,
                        animationSpec = tween(100,easing = FastOutSlowInEasing),
                        label = ""
                    )
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
                                        fontFamily = Fjalla,
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
                                        )
                                        .clipToBounds(),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    var mover by remember { mutableStateOf(false) }

                                    val offsetX by animateDpAsState(
                                        targetValue = if (mover) 200.dp else 0.dp,
                                        animationSpec = tween(1000),
                                        label = ""
                                    )
                                    Icon(
                                        painter = painterResource(R.drawable.motorcycle_24dp_000d70_fill0_wght400_grad0_opsz24),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier
                                            .offset(x = offsetX)
                                            .clickable {
                                                mover = !mover
                                            }
                                    )
                                    Text("Tu pedido está en camino...",modifier = Modifier
                                        .offset(x = offsetX)
                                        .clickable {
                                            mover = !mover
                                        }, fontFamily = PlusJakarta, fontWeight = FontWeight.Normal
                                    )

                                    val context = LocalContext.current
                                    IconButton(
                                        onClick = {
                                            val intent = Intent(context, EncontrarLocalActivity::class.java)
                                            context.startActivity(intent)
                                        },
                                        Modifier.padding(start = 10.dp)
                                            .background(Color.White)
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
                                        )
                                    ,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column()
                                    {
                                        Text(
                                            "Escanea el QR!",
                                            style = TextStyle(color = Color.White, fontSize = 18.sp)
                                            , fontFamily = PlusJakarta, fontWeight = FontWeight.Normal
                                        )
                                        Text("Obtén ya tu plato", color = Color.White, fontFamily = PlusJakarta, fontWeight = FontWeight.Normal, fontSize = 14.sp)
                                        Text("Personaliza tus sabores", color = Color.White, fontFamily = PlusJakarta, fontWeight = FontWeight.Normal, fontSize = 14.sp)
                                        Button(
                                            onClick = {val intent = Intent(context, MostrarLista::class.java)
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

                                                )
                                                , fontFamily = PlusJakarta, fontWeight = FontWeight.Bold
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
                                    painter = painterResource(R.drawable.postre3),
                                    contentDescription = "Postrechito",
                                    modifier = Modifier
                                        .shadow(8.dp, RoundedCornerShape(20.dp))
                                        .clip(RoundedCornerShape(20.dp))
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
                                    postres.take(8).chunked(2).forEach { par ->
                                        par.forEach { postre ->
                                            Card(
                                                shape = RoundedCornerShape(20.dp),
                                                elevation = CardDefaults.cardElevation(8.dp),
                                                        modifier = Modifier
                                                        .padding(top=15.dp, bottom=15.dp)
                                            ) {

                                                Box(
                                                    modifier = Modifier
                                                        .size(width = 120.dp, height = 180.dp)
                                                ) {

                                                    AsyncImage(

                                                        model = ImageRequest.Builder(LocalContext.current)
                                                            .data(postre.imagen)
                                                            .crossfade(true)
                                                            .build(),
                                                        contentDescription = null,
                                                        modifier = Modifier

                                                            .matchParentSize()
                                                            .clip(RoundedCornerShape(20.dp))
                                                            .border(
                                                                3.dp,
                                                                chocolate,
                                                                RoundedCornerShape(20.dp)
                                                            ),
                                                        contentScale = ContentScale.Crop,
                                                        placeholder = painterResource(R.drawable.nofoto),
                                                        error = painterResource(R.drawable.nofoto)
                                                    )

                                                    Text(
                                                        text = postre.nom_postre,
                                                        modifier = Modifier.padding(10.dp, top = 75.dp),
                                                        style = TextStyle(
                                                            color = Color.White,
                                                            fontWeight = FontWeight.Black,
                                                            fontSize = 10.sp,
                                                            shadow = Shadow(
                                                                color = Color.Black,
                                                                offset = Offset(2f, 2f),
                                                                blurRadius = 6f
                                                            )
                                                        )
                                                    )

                                                    Text(
                                                        text = postre.precio.toString(),
                                                        modifier = Modifier.padding(12.dp, top = 85.dp),
                                                        style = TextStyle(
                                                            color = Color.White,
                                                            fontWeight = FontWeight.Black,
                                                            fontSize = 15.sp,
                                                            shadow = Shadow(
                                                                color = Color.Black,
                                                                offset = Offset(2f, 2f),
                                                                blurRadius = 6f
                                                            )
                                                        )
                                                    )
                                                }
                                            }
                                        }


                                    }

                                }

                                // AQUÍ EMPIEZA LA SECCIÓN DE POSTRES
                                Column(
                                    Modifier.padding(top = 20.dp, start = 5.dp, end =5.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    Text(
                                        "Postres:",
                                        Modifier.align(Alignment.Start).padding(start = 10.dp, bottom = 20.dp),
                                        style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight(600))
                                        , fontFamily = Fjalla, fontWeight = FontWeight.Normal
                                    )

                                    postres.take(8).chunked(2).forEach { par ->
                                        Row(
                                            modifier = Modifier
                                                .height(260.dp)
                                                .width(360.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {

                                            par.forEach { postre ->

                                                Card(
                                                    shape = RoundedCornerShape(20.dp),
                                                    elevation = CardDefaults.cardElevation(8.dp),
                                                    modifier = Modifier
                                                        .width(150.dp)
                                                        .height(260.dp)
                                                        .padding(4.dp),
                                                    colors = CardDefaults.cardColors(
                                                        containerColor = Color.White
                                                    )
                                                ) {

                                                    Column(
                                                        modifier = Modifier.fillMaxSize(),
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
                                                                    .clip(
                                                                        RoundedCornerShape(
                                                                            topStart = 20.dp,
                                                                            topEnd = 20.dp
                                                                        )
                                                                    )
                                                                    .size(width = 170.dp, height = 130.dp)
                                                                    .clickable {
                                                                        val intent = Intent(
                                                                            context,
                                                                            DetallesPostreActivity::class.java
                                                                        )
                                                                        intent.putExtra("id_postre", postre.id_postre)
                                                                        context.startActivity(intent)
                                                                    },
                                                                contentScale = ContentScale.Crop,
                                                                placeholder = painterResource(R.drawable.nofoto),
                                                                error = painterResource(R.drawable.nofoto)
                                                            )

                                                            Box(
                                                                modifier = Modifier
                                                                    .align(Alignment.TopEnd)
                                                                    .padding(end = 5.dp, top = 8.dp)
                                                                    .size(40.dp)
                                                                    .clip(RoundedCornerShape(50.dp))
                                                                    .background(Color.White)
                                                                    .border(1.dp, azulFondo, RoundedCornerShape(50.dp))
                                                            ) {
                                                                Icon(
                                                                    painterResource(R.drawable.favorite_24dp_ffffff_fill0_wght400_grad0_opsz24),
                                                                    contentDescription = null,
                                                                    modifier = Modifier.align(Alignment.Center),
                                                                    tint = azulFondo
                                                                )
                                                            }
                                                        }



                                                        Text(
                                                            postre.nom_postre,
                                                            modifier = Modifier
                                                                .align(Alignment.CenterHorizontally)
                                                                .padding( top = 8.dp),
                                                            fontFamily = Fjalla,
                                                            fontSize = 15.sp
                                                        )
                                                        Row(modifier = Modifier.padding(5.dp)){
                                                            Text(
                                                                "S/ ${postre.precio}",
                                                                modifier = Modifier,
                                                                style = TextStyle(fontSize = 15.sp),
                                                                fontFamily = Fjalla,
                                                                color= azulFondo
                                                            )
                                                            Text(
                                                                "Stock: ${postre.stock}",
                                                                modifier = Modifier.padding(start = 10.dp),
                                                                style = TextStyle(fontSize = 15.sp),
                                                                fontFamily = Fjalla
                                                            )
                                                        }

                                                        Row(
                                                            modifier = Modifier.padding(5.dp),
                                                            horizontalArrangement = Arrangement.Center
                                                        ) {
                                                            Button(
                                                                onClick = {
                                                                    val intent = Intent(
                                                                        context,
                                                                        DetallesPostreActivity::class.java
                                                                    )
                                                                    intent.putExtra("id_postre", postre.id_postre)
                                                                    context.startActivity(intent)
                                                                },
                                                                colors = ButtonDefaults.buttonColors(containerColor = azulClaro),
                                                                modifier = Modifier
                                                                    .width(120.dp)
                                                                    .height(35.dp)
                                                                    .padding(top = 8.dp),
                                                                contentPadding = PaddingValues(0.dp)
                                                            ) {
                                                                Text(
                                                                    "Más información",
                                                                    style = TextStyle(
                                                                        color = Color.Black,
                                                                        fontSize = 12.sp,
                                                                        fontFamily = Nunito,
                                                                        fontWeight = FontWeight.Bold
                                                                    )
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
            }
        }



