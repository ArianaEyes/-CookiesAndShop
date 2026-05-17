package com.davidchura.proyectothor.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ariana.cookiesandshop.ui.theme.azulFondo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(titulo: String) {
    val context = LocalContext.current
    TopAppBar(
        title = {Text(titulo)},
        navigationIcon = {

            Surface(onClick = { (context as? ComponentActivity)?.finish() },
                shape = CircleShape,
                color = azulFondo.copy(0.3f),
                modifier = Modifier.padding(start = 16.dp)
            ){
                Box(Modifier.size(40.dp), contentAlignment = Alignment.Center){
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = azulFondo)
                }
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = azulFondo.copy(0.1f))
    )
}