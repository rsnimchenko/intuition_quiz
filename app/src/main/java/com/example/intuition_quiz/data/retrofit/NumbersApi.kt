package com.example.intuition_quiz.data.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {
    @GET("{number}?json")
    suspend fun getNumberInfo(
        @Path("number") number: String
    ): Response<NumberInfo>
}