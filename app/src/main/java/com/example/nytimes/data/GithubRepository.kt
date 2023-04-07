package com.example.nytimes.data

import android.util.Log
import com.example.nytimes.model.Repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

object GithubRepository {
    private val api = GithubApiService().api

    suspend fun getRepositories(username: String): List<Repository> {
        val response = api.getRepositoriesFromOrgName(username)
        val sortedResponse = response.sortedByDescending { it.stars }
        // Log.d("sorted response body", sortedResponse.toString())
        return sortedResponse
    }
}