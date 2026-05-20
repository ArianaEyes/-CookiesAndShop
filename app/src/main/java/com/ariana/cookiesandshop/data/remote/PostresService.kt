package com.ariana.cookiesandshop.data.remote

import com.ariana.cookiesandshop.models.Postres
import retrofit2.http.GET
import retrofit2.http.Query

interface PostresService {

    @GET("postres.php")
    suspend fun getPostres(
    @Query("id_postre") id_postre: Int? = null): List<Postres>

}