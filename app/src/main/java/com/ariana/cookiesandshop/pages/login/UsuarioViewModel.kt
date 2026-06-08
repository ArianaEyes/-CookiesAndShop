package com.ariana.cookiesandshop.pages.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ariana.cookiesandshop.data.remote.RetrofitClient
import com.ariana.cookiesandshop.models.LoginRequest
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<UsuarioUIState>(
        UsuarioUIState.Loading)
    val uiState: StateFlow<UsuarioUIState> = _uiState.asStateFlow()
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var estadoCheck by mutableStateOf(false)

    fun fetchLogin() {

        viewModelScope.launch {
            _uiState.value = UsuarioUIState.Loading
            try{
                val respuesta = RetrofitClient.loginService.getLogin(LoginRequest(email, password))
                _uiState.value = UsuarioUIState.Success(Gson().toJson(respuesta))
            } catch (e: Exception) {
                _uiState.value = UsuarioUIState.Error(
                    "Error al cargar datos: ${e.localizedMessage}"
                )
            }
        }
    }
}