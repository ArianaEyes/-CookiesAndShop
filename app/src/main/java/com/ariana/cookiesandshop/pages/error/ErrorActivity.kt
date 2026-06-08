package com.ariana.cookiesandshop.pages.error

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ariana.cookiesandshop.pages.error.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.davidchura.proyectothor.components.TopBar2

class ErrorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = { TopBar2("Volver") }) { innerPadding ->
                    Box(

                    ) {
                        Column(modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Color.White),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                            Spacer(modifier = Modifier.height(50.dp))

                            Image(painterResource(com.ariana.cookiesandshop.R.drawable.galleta_img),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(width = 200.dp, height = 200.dp)
                                    .padding(bottom = 20.dp))

                            Text(
                                "Error al cargar la página," +
                                        "vuelve a intentarlo",
                                Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(start = 10.dp, bottom = 20.dp)
                                    .width(250.dp),
                                color = Color.Black,
                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight(600), textAlign = TextAlign.Center)
                            )

                            Text(
                                "No te preocupes, todo se guardó!",
                                Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(start = 10.dp, bottom = 20.dp)
                                    .width(250.dp),
                                color = Color.Black,
                                style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(600), textAlign = TextAlign.Center)
                            )

                            Spacer(modifier = Modifier.height(150.dp))
                            Button(onClick={},
                                Modifier
                                    .height(55.dp)
                                    .width(320.dp), colors = ButtonDefaults.buttonColors(containerColor = azulFondo)){
                                Text("Volver a cargar...", style = TextStyle(Color.White, fontWeight = FontWeight(600), fontSize = 20.sp))
                            }
                        }
                    }
                }
            }
        }
    }
}

