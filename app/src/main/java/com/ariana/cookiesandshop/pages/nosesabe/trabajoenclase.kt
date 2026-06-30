package com.ariana.cookiesandshop.pages.nosesabe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import com.ariana.cookiesandshop.pages.nosesabe.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.ariana.cookiesandshop.ui.theme.azulOscuro

class trabajoenclase : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {
                setContent {
                    Canvas(modifier = Modifier.fillMaxSize()){
                        drawLine(start = Offset(100f,200f), end = Offset(400f,100f), strokeWidth = 10f, color = azulFondo)
                        drawLine(start = Offset(100f,200f), end = Offset(400f,100f), strokeWidth = 10f, color = Color.Red)
                        drawCircle(color= azulOscuro, radius= 300f, center = Offset(400f,100f))
//                        drawArc(color = Color.Green, starAngle = 0f, sweepAngle = 90f,
//                            style = Stroke(20f),
//                            useCenter = true,
//                            topLeft = Offset(400f, 1000f),
//                            size = Size(500f, 500f))
                        val colorRelleno = Color(0f, 0f, 255f, 0.3f)
                        val colorBorde = Color(0f, 0f, 255f, 0.3f)
                        drawRect(colorRelleno, topLeft = Offset(500f, 800f),
                            size= Size(200f,600f))

                        drawRect(colorRelleno,
                            style= Stroke(width = 20f),
                            topLeft = Offset(500f, 800f),
                            size= Size(200f,600f))

                    }
                }
            }
        }
    }
}
