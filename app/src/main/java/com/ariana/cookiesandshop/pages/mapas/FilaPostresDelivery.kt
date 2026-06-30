package com.ariana.cookiesandshop.pages.mapas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ariana.cookiesandshop.R
import com.ariana.cookiesandshop.models.Postres
import com.ariana.cookiesandshop.pages.detalles.DetallesViewModel

@Composable
fun FilaPostresDelivery(iPostres: Postres, viewModel: DetallesViewModel) {
    val context = LocalContext.current


    Card(
        modifier = Modifier
            .padding( vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Imagen
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(iPostres.imagen)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.nofoto),
                error= painterResource(R.drawable.nofoto)
            )

            // Info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = iPostres.nom_postre,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A1A)
                    )
                )
                Text(
                    text = iPostres.receta,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color(0xFF888888)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )


                }
            }
        }
    }


