package com.ukadovlad21.testtaskteamsdevit.data.api

import com.ukadovlad21.testtaskteamsdevit.models.DataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/tv/popular")
    suspend fun getListOfModels(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<DataModel>



}