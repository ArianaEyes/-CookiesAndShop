package com.ariana.cookiesandshop.pages.delivery

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import com.ariana.cookiesandshop.components.DibujarMapa
import com.ariana.cookiesandshop.ui.theme.CookiesAndShopTheme
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient

class DeliveryActivity : ComponentActivity() {
    private lateinit var placesClient: PlacesClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Places.initializeWithNewPlacesApiEnabled(
            applicationContext, "AIzaSyDUxQzUseMy9oT87ojFDCOvvvgdr9vs7jg"
        )
        placesClient = Places.createClient(this)
        setContent {
            CookiesAndShopTheme() {
                DibujarMapa(placesClient)
            }
        }
    }

}