package com.example.nytimes.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * GithubApiService creates an instance of a retrofit object, customizing it to use OkHttp and
 * a Gson converter,to implement the endpoint given by the [GithubApi] interface.
 */
class GithubApiService {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: GithubApi = retrofit.create(GithubApi::class.java)
}
