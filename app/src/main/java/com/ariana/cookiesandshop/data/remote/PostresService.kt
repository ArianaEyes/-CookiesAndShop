package com.ariana.cookiesandshop.data.remote

import com.ariana.cookiesandshop.models.Postres
import retrofit2.http.GET
import retrofit2.http.Query

interface PostresService {

    @GET("postres.php")
    suspend fun getPostres(): List<Postres>

    @GET("postres.php")
    suspend fun getPostreDetalle(
        @Query("id_postre") id_tipo: Int
    ): List<Postres>
}