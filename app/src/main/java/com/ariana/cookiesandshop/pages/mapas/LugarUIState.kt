package com.ariana.cookiesandshop.pages.mapas

import com.ariana.cookiesandshop.models.LugarDetalle

interface LugarUIState {
    data object Loading: LugarUIState
    data class Success(val Lugardetalle: LugarDetalle): LugarUIState

    data class SuccessList(val lugares: List<LugarDetalle>): LugarUIState
    data class Error(val message: String): LugarUIState
}