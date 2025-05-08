package com.example.challenge.data.remote.api

import com.example.challenge.data.remote.dto.ApiResponseDto
import retrofit2.http.GET

interface ApiService {
    @GET("api/6/bulkRequest/api_list/listApps")
    suspend fun getApps(): ApiResponseDto
}