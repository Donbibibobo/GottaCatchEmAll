package com.recurt.feature.creature.data.remote.api

import com.recurt.feature.creature.data.remote.dto.DigimonDetailDto
import com.recurt.feature.creature.data.remote.dto.DigimonListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DigimonApi {
    @GET("digimon")
    suspend fun getDigimonList(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 20
    ): DigimonListResponseDto

    @GET("digimon/{id}")
    suspend fun getDigimonDetail(
        @Path("id") id: String
    ): DigimonDetailDto
}