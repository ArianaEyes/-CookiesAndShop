package com.ariana.cookiesandshop

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ariana.cookiesandshop.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.ariana.cookiesandshop.ui.theme.fondoColor
import com.ariana.cookiesandshop.ui.theme.grisclaro
import com.ariana.cookiesandshop.components.BarraIcon

class CuentaConfigActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {
                Scaffold(containerColor = Color.Transparent,bottomBar = {BarraIcon(selectedItem = 3)}) {
                        innerPadding ->
                    Box(modifier= Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(fondoColor)
                        .statusBarsPadding()

                    )
                    {
                        Column(Modifier
                            .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally){
                            Row(Modifier
                                .width(380.dp)
                                .height(60.dp)) {
                                Image(painterResource(R.drawable.postre3),
                                    contentDescription = null,
                                    Modifier
                                        .size(width = 60.dp, height = 60.dp),
                                    contentScale = ContentScale.Crop)
                                Text("Bienvenido, USUARIO9378131", Modifier.width(200.dp)
                                    .padding(top = 10.dp, start = 10.dp),
                                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(700))
                                )
                                Spacer(Modifier.width(60.dp))
                                Box(Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(Color.White)

                                ){
                                    Icon(
                                        painterResource(R.drawable.notifications_active_24dp_000000_fill0_wght400_grad0_opsz24),
                                        contentDescription = null,
                                        Modifier.align(Alignment.Center)
                                    )
                                }


                            }
                            Spacer(Modifier.height(20.dp))
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
                                        onClick = {}, colors = ButtonDefaults.buttonColors(
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

                            val lista = listOf("Notificaciones", "Tema", "Información", "Cerrar sesión")

                            LazyColumn(Modifier
                                .padding(top = 20.dp)
                                .background(Color.White, RoundedCornerShape(30.dp))
                                .width(380.dp)

                                ) {
                                itemsIndexed(lista) { index, item ->

                                    Column{


                                        Row(

                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp)
                                            ,
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {

                                            Text(item)
                                            Icon(
                                                painterResource(R.drawable.baseline_arrow_forward_ios_24),
                                                contentDescription = null
                                            )
                                        }
                                        if (index < lista.size - 1) {
                                            HorizontalDivider(
                                                Modifier.
                                                width(360.dp)
                                                    .padding(start = 16.dp))
                                        }
                                    }
                                }
                            }

                            Spacer(Modifier.height(20.dp))
                            Row(Modifier
                                .width(380.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.White),
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Column(Modifier
                                    .width(300.dp)
                                    .padding(20.dp)
                                ){
                                    Text("Tema",style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(500)))
                                    Text("Cambiar a tema oscuro",style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(300)))

                                }

                                var checked by remember { mutableStateOf(false) }

                                Switch(
                                    checked = checked,
                                    onCheckedChange = { checked = it },
                                    Modifier
                                        .padding(10.dp),
                                    colors = SwitchDefaults.colors(
                                        checkedTrackColor = azulFondo,
                                        uncheckedTrackColor = grisclaro,
                                        checkedIconColor = Color.White,
                                        uncheckedIconColor = Color.White,
                                        uncheckedThumbColor = Color.White,
                                        checkedThumbColor = Color.White,
                                        checkedBorderColor = Color.Transparent,
                                        uncheckedBorderColor = Color.Transparent
                                    ),
                                )
                            }

                        }

                        Box(Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 20.dp, bottom = 20.dp)
                            .size(50.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color.White)

                        ){
                            val context = LocalContext.current
                            IconButton(
                                onClick = {
                                    val intent = Intent(context, LoginActivity::class.java)
                                    context.startActivity(intent)
                                },
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.login_24dp_000000_fill0_wght400_grad0_opsz24),
                                    contentDescription = null
                                )
                            }
                        }
                    }

                }
            }
        }
    }


}

