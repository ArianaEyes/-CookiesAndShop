package com.ariana.cookiesandshop.pages.MostrarLista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ariana.cookiesandshop.data.remote.RetrofitClient
import com.ariana.cookiesandshop.pages.Detalles.PostreDetalleUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListaViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<ListaUIState>(
        ListaUIState.Loading)
    val uiState: StateFlow<ListaUIState> = _uiState.asStateFlow()

    fun fetchLista() {
        viewModelScope.launch {
            _uiState.value = ListaUIState.Loading
            try{
                val respuesta = RetrofitClient.postresService.getPostres()
                _uiState.value = ListaUIState.Success(respuesta[0])
            } catch (e: Exception) {
                _uiState.value = ListaUIState.Error(
                    "Error al cargar datos: ${e.localizedMessage}"
                )
            }
        }
    }
}