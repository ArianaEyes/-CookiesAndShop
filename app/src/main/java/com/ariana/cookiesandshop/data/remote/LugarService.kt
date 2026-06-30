package com.ariana.cookiesandshop.data.remote

import com.ariana.cookiesandshop.models.LugarDetalle
import retrofit2.http.GET
import retrofit2.http.Query

interface LugarService {
    // Para Activity 2: un solo lugar (filtrado por id_postre)
    @GET("lugar.php")
    suspend fun getPostreLugar(
        @Query("id_postre") id_postre: Int
    ): List<LugarDetalle>

    @GET("lugar.php")
    suspend fun getLugarDetallePorId(
        @Query("id_lugar") id_lugar: Int
    ): List<LugarDetalle>
    // Para Activity 4: todos los lugares (sin filtro)
    @GET("lugar.php")
    suspend fun getTodosLosLugares(): List<LugarDetalle>
}