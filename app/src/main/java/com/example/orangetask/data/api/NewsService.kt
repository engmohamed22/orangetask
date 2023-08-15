package com.example.orangetask.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import  com.example.orangetask.models.NewsReppons

private const val API_KEY = "cadc8f8755f043cc825a345ab84b565c"

interface NewsService {

    @GET("/v2/everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsReppons>

//    @GET("/v2/top-headlines")
//    suspend fun getHeadlines(
//        @Query("country") countryCode: String = "",
//        @Query("page") page: Int = 1,
//        @Query("apiKey") apiKey: String = API_KEY
//    ): Response<NewsReppons>
}