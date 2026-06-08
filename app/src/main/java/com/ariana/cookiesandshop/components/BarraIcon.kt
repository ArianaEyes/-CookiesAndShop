package com.ariana.cookiesandshop.components

import android.app.Activity
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ariana.cookiesandshop.CuentaConfigActivity
import com.ariana.cookiesandshop.pages.login.LoginActivity
import com.ariana.cookiesandshop.R
import com.ariana.cookiesandshop.pages.home.HomeActivity

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
                (context as? Activity)?.overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
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
                (context as? Activity)?.overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
            },
            icon = { Icon(Icons.Default.Home, null) }
        )


        NavigationBarItem(
            selected = selectedItem == 3,
            onClick = {
                context.startActivity(Intent(context, CuentaConfigActivity::class.java))
                (context as? Activity)?.overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
                      },
            icon = {
                Icon(
                    painterResource(R.drawable.settings_24dp_000000_fill0_wght400_grad0_opsz24),
                    null)
            }
        )
    }
}