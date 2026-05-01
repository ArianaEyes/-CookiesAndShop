package com.ariana.cookiesandshop



import android.content.Intent
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.AttachEmail
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.data.UiToolingDataApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ariana.cookiesandshop.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.azulClaro
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.ariana.cookiesandshop.ui.theme.chocolate
import com.ariana.cookiesandshop.ui.theme.fondoColor
import com.ariana.cookiesandshop.ui.theme.grisclaro

class LoginActivity : ComponentActivity() {
    @OptIn(UiToolingDataApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {
                Scaffold(containerColor = Color.Transparent,bottomBar = {BarraIcon(selectedItem = 1)}) {
                        innerPadding ->
                    Box(modifier= Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(fondoColor)
                        .statusBarsPadding()
                        .verticalScroll(rememberScrollState())
                    )
                    {

                        Column(Modifier
                            .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally){

                            //FOTO DE PERFIL
                            Box(Modifier
                                .padding(top = 40.dp)
                            ){
                                Image(painterResource(R.drawable.postre3),
                                    contentDescription = null,
                                    Modifier
                                        .clip(RoundedCornerShape(5.dp))
                                        .size(width = 140.dp, height = 140.dp), contentScale = ContentScale.Crop
                                )
                                Box(Modifier
                                    .padding(start = 110.dp, top = 110.dp)
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(Color.White)
                                    .align(Alignment.BottomEnd)
                                ){
                                    Icon(painterResource(R.drawable.edit_24dp_000000),
                                        contentDescription = null,
                                        Modifier.align(Alignment.Center))
                                }
                            }

                            // TITULO NOMBRE

                            Text("BIENVENIDO USUARIO9378131!!", Modifier
                                .width(250.dp)
                                .padding(top = 15.dp),
                                style = TextStyle(fontSize =16.sp, fontWeight = FontWeight(500) ))

                            //INPUTS

                            Column(Modifier.padding(top = 40.dp)){
                                var user by remember { mutableStateOf("") }
                                Casilla(
                                    value = user,
                                    onValueChange = {user = it},
                                    placeholder = { Text("Inserte usuario", color = grisclaro) },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Default.Image,
                                            contentDescription = null, tint = grisclaro)})
                                var tel by remember {mutableStateOf("")}
                                Casilla(
                                    value = tel,
                                    onValueChange = {tel = it},
                                    placeholder = {Text("Inserte número telefónico", color = grisclaro)},
                                    leadingIcon = {
                                        Icon(Icons.Default.Home,
                                            contentDescription = null, tint = grisclaro)})
                                var gmail by remember {mutableStateOf("")}
                                Casilla(
                                    value = gmail,
                                    onValueChange = {gmail = it},
                                    placeholder = {Text("Introducir gmail", color = grisclaro)},
                                    leadingIcon = {Icon(Icons.Default.AttachEmail, contentDescription = null, tint = grisclaro)}
                                )
                                var fecha by remember {mutableStateOf("")}
                                Casilla(
                                    value = gmail,
                                    onValueChange = {gmail = it},
                                    placeholder = {Text("Introducir cumpleaños", color = grisclaro)},
                                    leadingIcon = {Icon(Icons.Default.CalendarMonth,contentDescription = null, tint = grisclaro)}
                                )

                                Column(Modifier
                                    .width(200.dp)
                                    .padding(top = 15.dp)) {
                                    Text("Oferta de 20% en todos los postre", style = TextStyle(fontSize = 13.sp))
                                    Text("SOLO HOY", style = TextStyle(fontSize = 13.sp))
                                }
                            }


                        }

                    }
                }



            }
        }
    }
}

@Composable
fun Casilla(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(

        value = value,
        onValueChange = onValueChange,
        Modifier
            .padding(top = 10.dp)
            .width(360.dp),

        placeholder = placeholder,
        leadingIcon = leadingIcon,

        shape = RoundedCornerShape(50.dp),

        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,


        ),

    )
}

@Composable
fun BarraIcon(selectedItem: Int) {
    val context = LocalContext.current

    NavigationBar(
        containerColor = Color.Transparent,
        tonalElevation = 0.dp
    ) {


        //LOGIN
        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = {
                context.startActivity(Intent(context, LoginActivity::class.java))
            },
            icon = {
                Icon(
                    painterResource(R.drawable.account_circle_24dp_000000_fill0_wght400_grad0_opsz24),
                    null
                )
            }
        )

        //HOME
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = {
                context.startActivity(Intent(context, HomeActivity::class.java))
            },
            icon = { Icon(Icons.Default.Home, null) }
        )

        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = {
                context.startActivity(Intent(context, InfoPostreActivity::class.java))
            },
            icon = {
                Icon(
                    painterResource(R.drawable.cookie_24dp_000000_fill0_wght400_grad0_opsz24),
                    null
                )
            }
        )

        NavigationBarItem(
            selected = selectedItem == 3,
            onClick = {
                context.startActivity(Intent(context, CuentaConfigActivity::class.java))
            },
            icon = {
                Icon(
                    painterResource(R.drawable.settings_24dp_000000_fill0_wght400_grad0_opsz24),
                    null)
            }
        )
    }
}