package com.example.nytimes.data

import android.util.Log
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

object GithubRepository {
    private val api = GithubApiService().api

    suspend fun getRepositories(username: String): GithubApi.RepositoryList? {
        Log.d("in here", "in here")
        Log.d("response", api.getRepositoriesFromUsername("iamrita").toString())
        val response = api.getRepositoriesFromUsername("iamrita")
        Log.d("response body", response.body().toString())

        return response.body()
    }
}