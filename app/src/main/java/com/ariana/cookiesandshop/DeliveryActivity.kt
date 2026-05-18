package com.ariana.cookiesandshop

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ariana.cookiesandshop.pages.Home.HomeActivity
import com.ariana.cookiesandshop.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.ariana.cookiesandshop.ui.theme.grisclaraso


class DeliveryActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {
                Box(modifier= Modifier
                    .fillMaxSize()
                )
                {

                    Column(Modifier
                        .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,) {
                        Image(
                            painterResource(R.drawable.google_maps),
                            contentDescription = null,
                            Modifier
                                .fillMaxSize()
                                .height(450.dp),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.TopCenter
                        )
                    }

                    val context = LocalContext.current

                    Box(Modifier
                        .padding(start = 20.dp, top = 50.dp)
                        .size(50.dp)
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


                    var mostrar by remember { mutableStateOf(false) }
                    IconButton(onClick = { mostrar = true }, Modifier.align(Alignment.BottomCenter)
                        .size(80.dp).padding(bottom = 20.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.keyboard_arrow_up_24dp_000000_fill0_wght400_grad0_opsz24),
                            contentDescription = null,
                            Modifier.size(50.dp)
                        )
                    }
                    val sheetState = rememberModalBottomSheetState()
                    if(mostrar){
                        ModalBottomSheet(
                            onDismissRequest = { mostrar = false },
                            sheetState = sheetState,
                            containerColor = grisclaraso,      // fondo blanco
                            shape = RoundedCornerShape(        // esquinas redondeadas arriba
                                topStart = 24.dp,
                                topEnd = 24.dp
                            )
                        ) {
                            Column(
                                Modifier
                                    .background(grisclaraso)
                                    .padding(horizontal = 24.dp)
                                    .padding(bottom = 2.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("Av. Alfredo Benavides 778, Miraflores",  Modifier
                                    .width(360.dp)
                                    .height(60.dp)
                                    .background(Color.White, shape = RoundedCornerShape(50.dp))
                                    .padding(
                                        top = 15.dp,
                                        bottom = 10.dp,
                                        start = 20.dp,
                                        end = 15.dp
                                    ),fontWeight = FontWeight.Bold)
                            }

                            Row(Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(top = 0.dp, start = 5.dp)) {
                                Column(
                                    Modifier
                                        .background(grisclaraso)
                                        .padding(20.dp)
                                ) {
                                    Text("Plazas",  Modifier
                                        .width(170.dp)
                                        .height(60.dp)
                                        .background(Color.White, shape = RoundedCornerShape(50.dp))
                                        .padding(
                                            top = 15.dp,
                                            bottom = 10.dp,
                                            start = 25.dp,
                                            end = 15.dp
                                        ),fontWeight = FontWeight.Bold)
                                }
                                Column(
                                    Modifier
                                        .background(grisclaraso)
                                        .padding(start= 0.dp, top = 20.dp),
                                ) {
                                    Text("Parques",  Modifier
                                        .width(170.dp)
                                        .height(60.dp)
                                        .background(Color.White, shape = RoundedCornerShape(50.dp))
                                        .padding(
                                            top = 15.dp,
                                            bottom = 10.dp,
                                            start = 25.dp,
                                            end = 15.dp
                                        ),fontWeight = FontWeight.Bold)
                                }}
                            Row() {
                                Column(
                                    Modifier
                                        .background(grisclaraso)
                                        .padding(top = 0.dp, start = 25.dp)
                                ) {
                                    Text("Tiendas cercas ",  Modifier
                                        .width(170.dp)
                                        .height(60.dp)
                                        .background(Color.White, shape = RoundedCornerShape(50.dp))
                                        .padding(
                                            top = 20.dp,
                                            bottom = 10.dp,
                                            start = 25.dp,
                                            end = 15.dp
                                        ),fontWeight = FontWeight.Bold)
                                }
                                Column(
                                    Modifier
                                        .background(grisclaraso)
                                        .padding(top = 0.dp, start = 20.dp)
                                ) {
                                    Text("Farmacias cercas ",  Modifier
                                        .width(170.dp)
                                        .height(60.dp)
                                        .background(Color.White, shape = RoundedCornerShape(50.dp))
                                        .padding(
                                            top = 20.dp,
                                            bottom = 10.dp,
                                            start = 25.dp,
                                            end = 15.dp
                                        ),fontWeight = FontWeight.Bold)
                                }}
                            Spacer(Modifier.height(10.dp))
                            Text("Instituciones cerca...",  Modifier
                                .width(360.dp)
                                .height(60.dp)
                                .align(Alignment.CenterHorizontally)
                                .background(Color.White, shape = RoundedCornerShape(50.dp))
                                .padding(
                                    top = 15.dp,
                                    bottom = 10.dp,
                                    start = 20.dp,
                                    end = 15.dp
                                ),fontWeight = FontWeight.Bold)

                            Button(onClick={},
                                Modifier.align(Alignment.CenterHorizontally)
                                    .padding(top = 20.dp)
                                    .height(55.dp)
                                    .width(350.dp), colors = ButtonDefaults.buttonColors(containerColor = azulFondo)){
                                Text("Comprar", style = TextStyle(Color.White, fontWeight = FontWeight(600), fontSize = 20.sp))
                            }
                        }

                    }

                }


            }
        }
    }
}

