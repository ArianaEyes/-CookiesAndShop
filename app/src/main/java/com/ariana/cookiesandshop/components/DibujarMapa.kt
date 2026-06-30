package com.ariana.cookiesandshop.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ariana.cookiesandshop.R
import com.ariana.cookiesandshop.models.LugarDetalle
import com.ariana.cookiesandshop.ui.theme.azulClaro
import com.ariana.cookiesandshop.ui.theme.azulFondo
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.libraries.places.api.model.CircularBounds
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun DibujarMapa(placesClient: PlacesClient,lugar: LugarDetalle) {
    val context = LocalContext.current

    var query by remember { mutableStateOf("") }
    var predictions by remember { mutableStateOf(listOf<HashMap<String,String>>()) }
    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }
    var ubicacion by remember { mutableStateOf(
        LatLng(lugar.latitud, lugar.longitud))}
    val mapProperties by remember {
        mutableStateOf(MapProperties(
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style)
        ))
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(ubicacion, 18f)
    }
    Box(modifier = Modifier.fillMaxSize()){
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties
        ) {
            Marker(
                state = MarkerState(position = ubicacion),
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),
                title = lugar.titulo,
                snippet = lugar.subtitulo,
                draggable = true
            )
            val ubicacionAntena = LatLng(lugar.latitud, lugar.longitud)
            Circle(
                center = ubicacionAntena,
                radius = 200.0,
                fillColor = Color(0x333134FF),
                strokeColor = Color(0x9927239A),
                clickable = true,
                onClick = {
                    Toast.makeText(context, "Red 5G de 200 metros",
                        Toast.LENGTH_SHORT).show()
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(20.dp)
                    ),
                value = query,
                onValueChange = { newValue ->
                    query = newValue

                    if(query.length > 3) {

                        val zonaCircular = CircularBounds
                            .newInstance(ubicacion, 2000.0)

                        val request = FindAutocompletePredictionsRequest.builder()
                            .setQuery(query)
                            .setCountries("PE", "CL", "BO")
                            .setLocationRestriction(zonaCircular)
                            .build()

                        placesClient.findAutocompletePredictions(request)
                            .addOnSuccessListener { response ->

                                predictions =
                                    response.autocompletePredictions.map { prediction ->

                                        hashMapOf(
                                            "place_id" to prediction.placeId,
                                            "description" to prediction.getFullText(null).toString()
                                        )
                                    }
                            }
                    }
                },

                label = {
                    Text(
                        "Buscar una pastelería 🍰",
                        color = Color(0xFF6A82B3)
                    )
                },

                leadingIcon = {
                    Text(
                        "🔍",
                        fontSize = 20.sp
                    )
                },

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = azulFondo,
                    unfocusedBorderColor =azulClaro,
                    focusedContainerColor = Color(0xFFF8FAFF),
                    unfocusedContainerColor = Color(0xFFF8FBFF)
                ),

                shape = RoundedCornerShape(20.dp)
            )


            if(predictions.isNotEmpty()) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(azulClaro)

                ) {

                    predictions.forEach { prediction ->

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {

                                    val placeId =
                                        prediction["place_id"].toString()

                                    val placeRequest =
                                        FetchPlaceRequest.newInstance(
                                            placeId,
                                            listOf(Place.Field.LOCATION)
                                        )

                                    placesClient.fetchPlace(placeRequest)
                                        .addOnSuccessListener {

                                            selectedLocation =
                                                it.place.location

                                            ubicacion = selectedLocation!!

                                            cameraPositionState.position =
                                                CameraPosition.fromLatLngZoom(
                                                    selectedLocation!!,
                                                    18f
                                                )

                                            predictions = emptyList()

                                            query =
                                                prediction["description"]
                                                    .toString()
                                        }
                                }
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier

                                    .clip(CircleShape)
                                    .background(azulClaro),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("🍰")
                            }


                            Spacer(
                                modifier = Modifier.width(12.dp)
                            )


                            Text(
                                text = prediction["description"].toString(),
                                color = Color(0xFF7A4A67),
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}