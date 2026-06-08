package com.ariana.cookiesandshop.pages.login

sealed interface UsuarioUIState {
    data object Loading: UsuarioUIState
    data class Success(val resultado : String): UsuarioUIState
    data class Error(val message: String): UsuarioUIState
}