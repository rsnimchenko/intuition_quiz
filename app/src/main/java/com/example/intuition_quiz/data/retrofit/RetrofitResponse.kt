package com.example.intuition_quiz.data.retrofit

sealed class RetrofitResponse {
    class Success(val numberInfo: String): RetrofitResponse()
    class Failure(val errorCode: String): RetrofitResponse()
}

data class NumberInfo(
    val text: String
)