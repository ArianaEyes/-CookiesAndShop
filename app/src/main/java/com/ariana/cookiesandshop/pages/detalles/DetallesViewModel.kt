package com.ariana.cookiesandshop.pages.detalles

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ariana.cookiesandshop.data.remote.PostresService
import com.ariana.cookiesandshop.data.remote.RetrofitClient
import com.ariana.cookiesandshop.models.Postres
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetallesViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<PostreDetalleUIState>(
        PostreDetalleUIState.Loading)
    val uiState: StateFlow<PostreDetalleUIState> = _uiState.asStateFlow()

    var id_postre by mutableStateOf("")
    var nom_postre by mutableStateOf("")
    var id_tipo by mutableStateOf(1)
    var receta by mutableStateOf("")
    var precio by mutableStateOf("")
    var stock by mutableStateOf("")
    var disponible by mutableStateOf(1)
    var imagen by mutableStateOf("")
    var descripcion by mutableStateOf("")
    var calorias by mutableStateOf("")

    fun fetchPostres() {
        _uiState.value = PostreDetalleUIState.Loading
        viewModelScope.launch {
            _uiState.value = PostreDetalleUIState.Loading
            try {
                val respuesta = RetrofitClient.postresService.getPostres()
                _uiState.value = PostreDetalleUIState.SuccessList(respuesta)
            } catch (e: Exception) {
                _uiState.value = PostreDetalleUIState.Error(
                    "Error al cargar datos: ${e.localizedMessage}"
                )
            }
        }
    }
    fun fetchPostrePorId(id_postre: Int) {
        _uiState.value = PostreDetalleUIState.Loading // 👈 confirma que esto esté
        viewModelScope.launch {
            _uiState.value = PostreDetalleUIState.Loading
            try {
                val respuesta = RetrofitClient.postresService.getPostres(id_postre)
                _uiState.value = PostreDetalleUIState.Success(respuesta[0])
            } catch (e: Exception) {
                _uiState.value = PostreDetalleUIState.Error(
                    "Error al cargar datos por id: ${e.localizedMessage}"
                )
            }
        }
    }

    fun insertPostre(){
        viewModelScope.launch {
            try{
                val nuevoPostre = Postres(
                    id_postre = 0,
                    nom_postre = nom_postre,
                    id_tipo = id_tipo,
                    receta = receta,
                    precio = precio.toDouble(),
                    disponible = disponible,
                    stock = stock.toInt(),
                    imagen = imagen,
                    descripcion = descripcion,
                    calorias = calorias.toInt()
                )
                RetrofitClient.postresService.insertPostre(nuevoPostre)
                fetchPostres()
            }
            catch (e: Exception){
                _uiState.value = PostreDetalleUIState.Error(
                    "Error al insertar datos: ${e.localizedMessage}"
                )
            }
        }
    }
    fun updatePostre(){
        viewModelScope.launch {
            try{
                val nuevoPostre = Postres(
                    id_postre.toInt(),
                    nom_postre = nom_postre,
                    id_tipo = id_tipo,
                    receta = receta,
                    precio = precio.toDouble(),
                    disponible = disponible,
                    stock = stock.toInt(),
                    imagen = imagen,
                    descripcion = descripcion,
                    calorias = calorias.toInt()
                )
                RetrofitClient.postresService.updatePostre(nuevoPostre)
                fetchPostres()
            }
            catch (e: Exception){
                _uiState.value = PostreDetalleUIState.Error(
                    "Error al actualizar datos: ${e.localizedMessage}"
                )
            }
        }
    }

    fun deletePostre(id_postre: Int){
        viewModelScope.launch {
            try{
                val response = RetrofitClient.postresService.deletePostre(id_postre)
                Log.d("DELETE", "Código: ${response.code()}")
                Log.d("DELETE", "Exitoso: ${response.isSuccessful}")
                Log.d("DELETE", "Body: ${response.body()}")
                fetchPostres()
            }
            catch (e: Exception){
                _uiState.value = PostreDetalleUIState.Error(
                    "Error al eliminar postre: ${e.localizedMessage}"
                )
            }
        }
    }


}