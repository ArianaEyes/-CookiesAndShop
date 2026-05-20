package com.ariana.cookiesandshop.pages.Detalles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ariana.cookiesandshop.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetallesViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<PostreDetalleUIState>(
        PostreDetalleUIState.Loading)
    val uiState: StateFlow<PostreDetalleUIState> = _uiState.asStateFlow()

    fun fetchPostre(id_postre: Int) {
        _uiState.value = PostreDetalleUIState.Loading // 👈 confirma que esto esté
        viewModelScope.launch {
            _uiState.value = PostreDetalleUIState.Loading
            try {
                val respuesta = RetrofitClient.postresService.getPostres(id_postre)
                _uiState.value = PostreDetalleUIState.Success(respuesta[0])
            } catch (e: Exception) {
                _uiState.value = PostreDetalleUIState.Error(
                    "Error al cargar datos: ${e.localizedMessage}"
                )
            }
        }
    }
}