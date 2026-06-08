package com.ariana.cookiesandshop.data.remote

import com.ariana.cookiesandshop.models.LoginRequest
import com.ariana.cookiesandshop.models.Usuario
import com.google.gson.JsonArray
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @POST("login.php")
    suspend fun getLogin(
        @Body body: LoginRequest
    ): List<Usuario>
}