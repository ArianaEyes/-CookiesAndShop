package com.ariana.cookiesandshop.data.remote

import com.ariana.cookiesandshop.models.Postres
import retrofit2.http.GET

interface PostresService {

    @GET("postres.php")
    suspend fun getPostres(): List<Postres>
}