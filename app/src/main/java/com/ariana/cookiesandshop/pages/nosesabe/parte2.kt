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
import androidx.compose.ui.text.drawText

data class Datos(
    val etiqueta: String,
    val valor: Double
)

class parte2 : ComponentActivity() {
    val listaDatos: List<Datos> = listOf(
        Datos("Colombia", 600.0),
        Datos("Perú", 400.0),
        Datos("Tailandia", 1200.0),
        Datos("Brasil", 1800.0),
        Datos("Venechuela", 1800.0),
        Datos("Escocia", 1800.0),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val valorMaximo = listaDatos.maxOf { it.valor }

        enableEdgeToEdge()
        setContent {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val anchoTotal = size.width
                        val altoTotal = size.height
                        val razonAncho = valorMaximo / anchoTotal
                        val razonAlto = valorMaximo / altoTotal
                        val colorRelleno = Color(0f, 0f, 255f, 0.3f)
                        val colorBorde = Color(0f, 0f, 255f, 0.3f)

                        for(i in listaDatos.indices){
                            val posY= razonAlto + 0.6f
                            val alto  = i* razonAlto + razonAlto*0.2f
                            val ancho : Float = (listaDatos[i].valor/razonAncho).toFloat()
                            drawRect(colorRelleno, topLeft = Offset(
                                0f,
                                20f),
                                size= Size( ancho,100f))
//                            val textWidth= textLayoutResult.size.widthclashes
//                            pero m taba durmiendo
//
//                            drawText(textLayoutResult = textLayoutResult,
//                                colorBorde,style= Stroke(20f), topLeft = Offset(0f, posY),
//                                size= Size(ancho, alto)
                        }

                    }


        }
    }
}
