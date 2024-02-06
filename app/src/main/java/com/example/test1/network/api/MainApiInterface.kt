package com.example.test1.network.api

import com.example.test1.network.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiInterface {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey : String,
                                 @Query("language") language : String,
                                 @Query("page") page : Int  ) : Response<ApiResponse>
}