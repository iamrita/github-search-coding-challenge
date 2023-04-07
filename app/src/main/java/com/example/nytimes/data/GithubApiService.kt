package com.example.nytimes.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GithubApiService {

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        // Add any other interceptors as needed
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val api = retrofit.create(GithubApi::class.java)
}
