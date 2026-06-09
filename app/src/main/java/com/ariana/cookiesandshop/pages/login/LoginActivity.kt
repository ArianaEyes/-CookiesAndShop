package com.ariana.cookiesandshop.pages.login


import android.R.attr.top
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.data.UiToolingDataApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ariana.cookiesandshop.R
import com.ariana.cookiesandshop.ui.theme.CookiesAndShopTheme
import com.ariana.cookiesandshop.ui.theme.fondoColor
import com.ariana.cookiesandshop.ui.theme.grisclaro
import com.ariana.cookiesandshop.components.BarraIcon
import com.ariana.cookiesandshop.data.local.UserStore
import com.ariana.cookiesandshop.models.Usuario
import com.ariana.cookiesandshop.pages.home.HomeActivity
import com.ariana.cookiesandshop.ui.theme.Roboto
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.ariana.cookiesandshop.ui.theme.gris
import com.ariana.cookiesandshop.utils.usuarioActivo
import com.google.gson.Gson

class LoginActivity : ComponentActivity() {
    @OptIn(UiToolingDataApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            CookiesAndShopTheme {
                var imagenUri by remember { mutableStateOf<Uri?>(null) }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { uri: Uri? ->
                    imagenUri = uri
                }

                var pulse by remember { mutableStateOf(false) }
                val scope = rememberCoroutineScope()

                val scale by animateFloatAsState(
                    targetValue = if (pulse) 2f else 1f,
                    animationSpec = tween(100,easing = FastOutSlowInEasing),
                    label = "pulse_animation"
                )
                val uiState by viewModel.uiState.collectAsState()
                var iniciarLogin by remember { mutableStateOf(false) }
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
                            .fillMaxSize()
                            .graphicsLayer {
                                clip = false
                            },
                            horizontalAlignment = Alignment.CenterHorizontally){

                            //FOTO DE PERFIL
                            Box {
                                Card(
                                    Modifier.padding(top=40.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    elevation = CardDefaults.cardElevation(8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier.size(140.dp)
                                    ) {

                                        Image(
                                            painter = painterResource(R.drawable.postre3),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.matchParentSize()
                                        )
                                    }
                                }

                                Box(
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .offset(x = 8.dp, y = 8.dp)
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(50.dp))
                                        .background(Color.White)
                                ) {
                                    IconButton(onClick = {
                                        launcher.launch("image/*")
                                    }) {
                                        Icon(
                                            painter = painterResource(R.drawable.edit_24dp_000000),
                                            contentDescription = null,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }

                                }
                            }

                            // TITULO NOMBRE

                            Text("Bienvenido", Modifier
                                .width(100.dp)
                                .padding(top = 15.dp),
                                style = TextStyle(fontSize =16.sp, fontWeight = FontWeight.Medium, fontFamily = Roboto ))


                            //INPUTS

                            Column(Modifier.padding(top = 40.dp),
                                horizontalAlignment = Alignment.CenterHorizontally){
                                Casilla(
                                    label = R.string.emailString,
                                    value = viewModel.email,
                                    onValueChange = {viewModel.email = it},
                                    placeholder = { Text("Inserte usuario", color = grisclaro) },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Default.Person,
                                            contentDescription = null, tint = grisclaro,
                                            modifier = Modifier.padding(start = 10.dp))})

                                Casilla(
                                    label = R.string.passwordString,
                                    value = viewModel.password,
                                    onValueChange = {viewModel.password = it},
                                    placeholder = {Text("Inserte número contraseña")},
                                    leadingIcon = {
                                        Icon(Icons.Default.Password,
                                            contentDescription = null, tint = gris,
                                            modifier = Modifier.padding(start = 10.dp))},
                                    visualTransformation = PasswordVisualTransformation(),)

                                Button(onClick = { viewModel.fetchLogin()
                                    iniciarLogin = true},
                                    modifier = Modifier.padding(top = 20.dp, start = 10.dp)
                                        .width(250.dp)
                                        .height(50.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = azulFondo,
                                        contentColor = Color.White)) {
                                    Text(text = stringResource(R.string.iniciar_sesion), fontSize = 20.sp, fontFamily = Roboto, fontWeight = FontWeight.Medium)
                                }

                                when(val state = uiState) {
                                    is UsuarioUIState.Loading -> {
                                        if(iniciarLogin) {
                                            CircularProgressIndicator()
                                        }
                                    }
                                    is UsuarioUIState.Error -> {
                                        Text(state.message, color = MaterialTheme.colorScheme.error)
                                    }
                                    is UsuarioUIState.Success -> {
                                        evaluarResultado(state.resultado, viewModel)
                                    }
                                }
                            }


                        }

                    }
                }



            }
        }
    }
    private fun evaluarResultado(resultado: String, viewModel: UsuarioViewModel) {
        when (resultado) {
            "-1" -> Toast.makeText(this, "La cuenta no existe",
                Toast.LENGTH_SHORT).show()
            "-2" -> Toast.makeText(this, "La contraseña es incorrecta",
                Toast.LENGTH_SHORT).show()
            else -> {
                usuarioActivo = Gson().fromJson(resultado, Array<Usuario>::class.java).first()
                if(viewModel.estadoCheck){
                    lifecycleScope.launch {
                        val userStore = UserStore(this@LoginActivity)
                        userStore.guardarDatosUsuario(resultado)
                    }
                }

                Toast.makeText(
                    this, "Bienvenido",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }
}

@Composable
fun Casilla(
    label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: Any? = null
) {
    OutlinedTextField(
        label = { Text(text = stringResource(id = label), fontFamily = Roboto, fontWeight = FontWeight.Normal) },
        value = value,
        onValueChange = onValueChange,
        visualTransformation = if (visualTransformation != null) visualTransformation as VisualTransformation else VisualTransformation.None,
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
        modifier = Modifier
            .padding(top = 10.dp)
            .width(320.dp),
    )
}


