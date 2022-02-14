package com.application.latticeinnovations.repository

import com.application.latticeinnovations.di.NewsModule
import com.application.latticeinnovations.extra.Resource
import com.application.latticeinnovations.extra.ResponseHandler
import com.application.latticeinnovations.model.remote.ResponseDTO
import retrofit2.http.Query
import java.lang.Exception
import javax.inject.Inject

class NewsRepo @Inject constructor() {

    val responseHandler = ResponseHandler()

    suspend fun getNews(): Resource<ResponseDTO> {
        return try {
            val data = NewsModule.ProvidesApiService().getData()
            responseHandler.handleSuccess(data)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun getSearchData(query: String) : Resource<ResponseDTO>{
        return try {
            val data = NewsModule.ProvidesApiService().getSearchData("in",query)
            responseHandler.handleSuccess(data)
        }catch (e:Exception){
            responseHandler.handleException(e)
        }
    }
}