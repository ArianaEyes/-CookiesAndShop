package com.ariana.cookiesandshop.pages.Detalles

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.ariana.cookiesandshop.BarraIcon
import com.ariana.cookiesandshop.pages.Home.HomeActivity
import com.ariana.cookiesandshop.R
import com.ariana.cookiesandshop.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.ariana.cookiesandshop.ui.theme.azulOscuro
import com.ariana.cookiesandshop.ui.theme.degradado

class DetallesPostreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {
                Scaffold(
                    containerColor = Color.Companion.Transparent,
                    bottomBar = { BarraIcon(selectedItem = 2) }) { innerPadding ->
                    Box(
                        modifier = Modifier.Companion
                            .fillMaxSize()
                    )
                    {

                        Image(
                            painterResource(R.drawable.postre7),
                            contentDescription = null,
                            Modifier.Companion
                                .fillMaxSize()
                                .offset(y = (-100).dp),  // ← cambia este número
                            contentScale = ContentScale.Companion.Crop,
                            alignment = Alignment.Companion.TopCenter
                        )

                        Box(
                            modifier = Modifier.Companion
                                .fillMaxSize()
                                .background(
                                    brush = Brush.Companion.verticalGradient(
                                        colors = listOf(
                                            Color.Companion.Transparent,        // arriba transparente
                                            degradado// abajo tu color de fondo
                                        )
                                    )
                                )
                        )

                        Box(
                            Modifier.Companion
                                .padding(start = 20.dp, top = 50.dp)
                                .size(50.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color.Companion.White)
                        ) {
                            Icon(
                                painterResource(R.drawable.arrow_back_ios_24dp_000000_fill0_wght400_grad0_opsz24),
                                contentDescription = null,
                                Modifier.Companion.align(Alignment.Companion.Center)
                                    .padding(start = 8.dp),
                                tint = Color.Companion.Gray
                            )
                        }

                        Box(
                            Modifier.Companion
                                .padding(start = 20.dp, top = 50.dp)
                                .size(50.dp)
                                .clip(androidx.compose.foundation.shape.RoundedCornerShape(50.dp))
                                .background(Color.Companion.White)
                        ) {
                            val context = LocalContext.current
                            IconButton(
                                onClick = {
                                    val intent = Intent(context, HomeActivity::class.java)
                                    context.startActivity(intent)
                                },
                                Modifier.Companion.padding(start = 10.dp)
                            ) {
                                Icon(
                                    painterResource(R.drawable.arrow_back_ios_24dp_000000_fill0_wght400_grad0_opsz24),
                                    contentDescription = null,
                                    Modifier.Companion.align(Alignment.Companion.Center),
                                    tint = Color.Companion.Gray
                                )
                            }
                        }

                        Box(
                            Modifier.Companion
                                .padding(end = 20.dp, top = 50.dp)
                                .size(50.dp)
                                .clip(androidx.compose.foundation.shape.RoundedCornerShape(50.dp))
                                .background(Color.Companion.White)
                                .align(Alignment.Companion.TopEnd)
                        ) {
                            val context = LocalContext.current
                            IconButton(
                                onClick = {
                                    val intent = Intent(context, HomeActivity::class.java)
                                    context.startActivity(intent)
                                }
                            ) {
                                Icon(
                                    painterResource(R.drawable.favorite_24dp_ffffff_fill0_wght400_grad0_opsz24),
                                    contentDescription = null,
                                    Modifier.Companion.align(Alignment.Companion.Center),
                                    tint = Color.Companion.Gray
                                )
                            }
                        }

                        Column(
                            Modifier.Companion.padding(innerPadding)
                                .verticalScroll(rememberScrollState())
                                .align(Alignment.Companion.BottomCenter)
                                .height(340.dp)
                        )
                        {
                            Text(
                                "Bizcocho glaseado",
                                Modifier.Companion.width(350.dp).padding(bottom = 20.dp),
                                textAlign = TextAlign.Companion.Center,
                                style = TextStyle(
                                    azulOscuro, fontSize = 25.sp, fontWeight = FontWeight(900)
                                ),
                            )
                            Text(
                                "Loremloremloremloremloremloremloremloremloremloremloremloremloremloremloremloremlorem" +
                                        " loremloremloremloremloremloremloremloremloremloremloremlorem loremloremloremloremlorem",
                                Modifier.Companion.width(350.dp),
                                textAlign = TextAlign.Companion.Center,
                                style = TextStyle(
                                    Color.Companion.Black,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight(400)
                                )
                            )


                            Column(
                                Modifier.Companion
                                    .width(350.dp)
                                    .height(120.dp)
                                    .padding(top = 20.dp)
                                    .background(
                                        Color.Companion.White.copy(0.6f),
                                        shape = androidx.compose.foundation.shape.RoundedCornerShape(
                                            20.dp
                                        )
                                    )
                            ) {
                                Row {
                                    Button(
                                        onClick = {}, colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Companion.White
                                        ),
                                        modifier = Modifier.Companion
                                            .padding(start = 10.dp, top = 10.dp)
                                            .width(90.dp)
                                            .height(25.dp),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(
                                            "200kcal",
                                            style = TextStyle(
                                                color = Color.Companion.Black,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight(700)
                                            )
                                        )
                                    }
                                    Button(
                                        onClick = {}, colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Companion.Gray
                                        ),
                                        modifier = Modifier.Companion
                                            .padding(
                                                top = 10.dp
                                            )
                                            .width(70.dp)
                                            .height(25.dp),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(
                                            "200kcal",
                                            style = TextStyle(
                                                color = Color.Companion.White,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight(700)
                                            )
                                        )
                                    }

                                    Spacer(Modifier.Companion.width(90.dp))
                                    Button(
                                        onClick = {}, colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Companion.White
                                        ),
                                        modifier = Modifier.Companion
                                            .padding(top = 10.dp)
                                            .width(80.dp)
                                            .height(25.dp),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(
                                            "Aviso",
                                            style = TextStyle(
                                                color = Color.Companion.Black,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight(700)
                                            )
                                        )
                                        Spacer(Modifier.Companion.width(8.dp))
                                        Icon(
                                            painterResource(R.drawable.info_24dp_000000_fill0_wght400_grad0_opsz24),
                                            contentDescription = null,
                                            Modifier.Companion.size(15.dp),
                                            tint = Color.Companion.Gray
                                        )
                                    }
                                }

                                Row(
                                    Modifier.Companion.padding(30.dp, 10.dp),
                                    horizontalArrangement = Arrangement.spacedBy(60.dp)
                                ) {
                                    Column {
                                        Text(
                                            "500", style = TextStyle(
                                                fontSize = 18.sp, fontWeight = FontWeight(900)
                                            )
                                        )
                                        Text(
                                            "kcal", style = TextStyle(
                                                fontSize = 14.sp, fontWeight = FontWeight(400)
                                            )
                                        )
                                    }

                                    Column {
                                        Text(
                                            "500", style = TextStyle(
                                                fontSize = 18.sp, fontWeight = FontWeight(900)
                                            )
                                        )
                                        Text(
                                            "kcal", style = TextStyle(
                                                fontSize = 14.sp, fontWeight = FontWeight(400)
                                            )
                                        )
                                    }

                                    Column {
                                        Text(
                                            "500", style = TextStyle(
                                                fontSize = 18.sp, fontWeight = FontWeight(900)
                                            )
                                        )
                                        Text(
                                            "kcal", style = TextStyle(
                                                fontSize = 14.sp, fontWeight = FontWeight(400)
                                            )
                                        )
                                    }

                                    Column {
                                        Text(
                                            "500", style = TextStyle(
                                                fontSize = 18.sp, fontWeight = FontWeight(900)
                                            )
                                        )
                                        Text(
                                            "kcal", style = TextStyle(
                                                fontSize = 14.sp, fontWeight = FontWeight(400)
                                            )
                                        )
                                    }
                                }
                            }

                            Button(
                                onClick = {},
                                Modifier.Companion.align(Alignment.Companion.CenterHorizontally)
                                    .padding(top = 30.dp)
                                    .height(55.dp)
                                    .width(350.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = azulFondo)
                            ) {
                                Text(
                                    "Comprar",
                                    style = TextStyle(
                                        Color.Companion.White,
                                        fontWeight = FontWeight(600),
                                        fontSize = 20.sp
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