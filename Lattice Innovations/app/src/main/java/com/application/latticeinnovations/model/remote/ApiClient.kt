package com.application.latticeinnovations.model.remote

import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Api Class to load the End Points
 */
interface ApiClient {
    /**
     * https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=32bfcea9923a439dbadbcb6d184c3730
     */

    @GET("v2/top-headlines?country=in&category=entertainment&apiKey=32bfcea9923a439dbadbcb6d184c3730")
    suspend fun getData(
        @Query("page") Page: Int
    ): ResponseDTO
}