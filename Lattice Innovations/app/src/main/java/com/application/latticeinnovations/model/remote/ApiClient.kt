package com.application.latticeinnovations.model.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    /**
     * https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=ab424053c14248b6bbb7da59fa401cfd
     */

    @GET("v2/top-headlines?country=us&category=business&apiKey=ab424053c14248b6bbb7da59fa401cfd")
    suspend fun getData(): ResponseDTO

    @GET("v2/top-headlines")
    suspend fun getSearchData(
        @Query("country") country : String = "in",
        @Query("category") category : String,
        @Query("apiKey") apiKey : String = "ab424053c14248b6bbb7da59fa401cfd"
    ): ResponseDTO
}