package com.ariana.cookiesandshop.pages.mapas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ariana.cookiesandshop.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LugarViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<LugarUIState>(
        LugarUIState.Loading)
    val uiState: StateFlow<LugarUIState> = _uiState.asStateFlow()


    fun fetchLugar() {
        _uiState.value = LugarUIState.Loading
        viewModelScope.launch {
            _uiState.value = LugarUIState.Loading
            try {
                val respuesta = RetrofitClient.lugarService.getTodosLosLugares()
                _uiState.value = LugarUIState.SuccessList(respuesta)
            } catch (e: Exception) {
                _uiState.value = LugarUIState.Error(
                    "Error al cargar datos: ${e.localizedMessage}"
                )
            }
        }
    }
    fun fetchLugarPorId(idLugar: Int) {
        viewModelScope.launch {
            _uiState.value = LugarUIState.Loading
            try {
                val response = RetrofitClient.lugarService.getLugarDetallePorId(idLugar)
                val lugar = response.firstOrNull()
                _uiState.value = if (lugar != null) LugarUIState.Success(lugar)
                else LugarUIState.Error("Lugar no encontrado")
            } catch (e: Exception) {
                _uiState.value = LugarUIState.Error("Error: ${e.message}")
            }
        }
    }
    fun fetchLugarPorPostreId(idPostre: Int) {
        viewModelScope.launch {
            _uiState.value = LugarUIState.Loading
            try {
                val response = RetrofitClient.lugarService.getPostreLugar(idPostre)
                val lugar = response.firstOrNull()
                if (lugar != null) {
                    _uiState.value = LugarUIState.Success(lugar)
                } else {
                    _uiState.value = LugarUIState.Error("No se encontró lugar para este postre")
                }
            } catch (e: Exception) {
                _uiState.value = LugarUIState.Error("Error: ${e.message}")
            }
        }
    }




}