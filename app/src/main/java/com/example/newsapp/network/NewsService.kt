package com.example.newsapp.network

import com.example.newsapp.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getTopArticles(
        @Query("country") country:String
    ): Call<TopNewsResponse>


    @GET("top-headlines")
    fun getArticlesByCategory(
        @Query("category") category:String
    ): Call<TopNewsResponse>

    @GET("everything")
    fun getArticlesBySource(
        @Query("sources") source:String
    ): Call<TopNewsResponse>

    @GET("everything")
    fun getArticles(
        @Query("q") q:String
    ): Call<TopNewsResponse>
}