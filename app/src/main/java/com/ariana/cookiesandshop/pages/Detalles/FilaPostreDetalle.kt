package com.ariana.cookiesandshop.pages.Detalles

import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import com.ariana.cookiesandshop.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import com.ariana.cookiesandshop.models.Postres
import com.ariana.cookiesandshop.models.TipoPostre
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.ariana.cookiesandshop.utils.API_URL

@Composable
fun FilaPostreDetalle(itemPostre: Postres, itemTipo : TipoPostre){
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)
                    .verticalScroll(rememberScrollState())) {
        val rutaImagen = if (itemPostre.imagen.isNullOrBlank()) {
            R.drawable.nofoto
        } else {
            API_URL + itemPostre.imagen
        }
        Box(contentAlignment = Alignment.TopEnd) {

            AsyncImage(
                model = rutaImagen,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(400.dp)
            )

            if (itemPostre.precio > 0f) {
                val porcentajeDescuento = (itemPostre.precio / itemPostre.precio - 1) * 100
                Text(
                    text = "${porcentajeDescuento.toInt()}%",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 24.dp)
                        .background(azulFondo, shape = MaterialTheme.shapes.extraSmall)
                        .padding(horizontal = 16.dp)
                )
            }
        }
        Text(
            text = itemPostre.nom_postre,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            textAlign = TextAlign.Center
        )

        val precioFinal = itemPostre.precio.takeIf { it>0f }?: itemPostre.precio
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = "S/ ${"%.2f".format(precioFinal)}",
            )
            if (itemPostre.precio > 0f) {
                Text(
                    text = "S/ ${"%.2f".format(itemPostre.precio)}",
                    color = Color.Red,
                    textDecoration = TextDecoration.LineThrough,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        FilaDato("Nombre", itemPostre.nom_postre)
        FilaDato("Categoría", itemTipo.nombre)
        FilaDato("Receta", itemPostre.receta)
        FilaDato("Stock", itemPostre.stock.toString())
        Text("Descripcion del tipo de postre: ")
        FilaDato("Categoría", itemTipo.descripcion)

        Text(text = "Descripción", style = MaterialTheme.typography.titleLarge)
        AndroidView(
            factory = { context ->
                TextView(context).apply {
                    textSize = 16f
                }
            },
            update = { textView ->
                textView.text = HtmlCompat.fromHtml(
                    itemPostre.descripcion,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            }
        )
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