package com.ariana.cookiesandshop.pages.Detalles

import com.ariana.cookiesandshop.models.Postres

interface PostreDetalleUIState {
    data object Loading: PostreDetalleUIState
    data class Success(val DetallesPostre: Postres): PostreDetalleUIState
    data class Error(val message: String): PostreDetalleUIState
}