package com.ariana.cookiesandshop.pages.detalles

import android.content.Intent
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.ariana.cookiesandshop.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import com.ariana.cookiesandshop.models.Postres
import com.ariana.cookiesandshop.pages.home.HomeActivity
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.ariana.cookiesandshop.ui.theme.azulOscuro
import com.ariana.cookiesandshop.ui.theme.degradado

@Composable
fun PostreDetalle(itemPostre: Postres){
    Box(
        modifier = Modifier.Companion
            .fillMaxSize()
    )
    {
        val rutaImagen = if (itemPostre.imagen.isNullOrBlank()) {
            R.drawable.nofoto
        } else {
            itemPostre.imagen
        }

        AsyncImage(
            model = rutaImagen,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-100).dp),
            contentScale = ContentScale.Companion.Crop,
            alignment = Alignment.Companion.TopCenter,
            placeholder = painterResource(R.drawable.nofoto),
            error= painterResource(R.drawable.nofoto)
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
            Modifier
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
            Modifier.Companion.padding(16.dp)
                .verticalScroll(rememberScrollState())
                .align(Alignment.Companion.BottomCenter)
                .height(340.dp)
        )
        {
            Text(
                itemPostre.nom_postre ?: "sin nombre",
                Modifier.Companion.width(350.dp).padding(bottom = 20.dp),
                textAlign = TextAlign.Companion.Center,
                style = TextStyle(
                    azulOscuro, fontSize = 25.sp, fontWeight = FontWeight(900)
                ),
            )
            Text(
                 itemPostre.descripcion ?: "sin descripcion",
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
                            text= "${itemPostre.calorias}kcal",
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

                    Spacer(Modifier.Companion.width(60.dp))
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
                        Spacer(Modifier.Companion.width(4.dp))
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
                            "Quedan :", style = TextStyle(
                                fontSize = 18.sp, fontWeight = FontWeight(900)
                            )
                        )
                        Text(
                            "${itemPostre.stock}", style = TextStyle(
                                fontSize = 14.sp, fontWeight = FontWeight(400)
                            )
                        )
                    }

                    Column {
                        Text(
                            "Precio:", style = TextStyle(
                                fontSize = 18.sp, fontWeight = FontWeight(900)
                            )
                        )
                        Text(
                            "${itemPostre.precio}", style = TextStyle(
                                fontSize = 14.sp, fontWeight = FontWeight(400)
                            )
                        )
                    }

                    Column {
                        Text(
                            "Receta", style = TextStyle(
                                fontSize = 18.sp, fontWeight = FontWeight(900)
                            )
                        )
                        Text(
                            "${itemPostre.receta}", style = TextStyle(
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

@Composable
fun FilaDato(etiqueta: String, valor: String?) {
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
    Row() {
        Text(text = etiqueta, modifier = Modifier.weight(2f)
            .padding(16.dp), fontWeight = FontWeight.Bold)
        Text(text = valor ?: "", modifier = Modifier.weight(3f)
            .padding(16.dp))
    }
}

