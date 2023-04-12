package com.example.intuition_quiz.data.retrofit

import javax.inject.Inject

class NumbersRepository @Inject constructor(
    private val numbersApi: NumbersApi
) {

    suspend fun getNumberInfo(
        number: String
    ): RetrofitResponse {
        val response = numbersApi.getNumberInfo(number)
        return if (response.isSuccessful)
            RetrofitResponse.Success(response.body()?.text.orEmpty())
        else
            RetrofitResponse.Failure("${response.raw().code}")
    }
}