package com.ariana.cookiesandshop.pages.MostrarLista

import com.ariana.cookiesandshop.models.Postres

interface ListaUIState {
    data object Loading: ListaUIState
    data class Success(val postre: Postres): ListaUIState
    data class Error(val message: String): ListaUIState
}