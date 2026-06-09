package com.ariana.cookiesandshop.data.remote

import com.ariana.cookiesandshop.models.PostreResponse
import com.ariana.cookiesandshop.models.Postres
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PostresService {

    @GET("postres.php")
    suspend fun getPostres(
    @Query("id_postre") id_postre: Int? = null): List<Postres>
    @POST("postres.php")
    suspend fun insertPostre(
        @Body postre: Postres
    ): Response<PostreResponse>

    @PUT("postres.php")
    suspend fun updatePostre(
        @Body postre: Postres
    ): Response<PostreResponse>
    @DELETE("postres.php")
    suspend fun deletePostre(
        @Path("id_postre") id_postre: Int?
    ): Response<PostreResponse>
}