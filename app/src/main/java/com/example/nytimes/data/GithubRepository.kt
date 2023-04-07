package com.example.nytimes.data

import android.util.Log
import com.example.nytimes.model.Repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

object GithubRepository {
    private val api = GithubApiService().api

    suspend fun getRepositories(orgName: String): List<Repository> {
        val response = api.getRepositoriesFromOrgName(orgName)
        val sortedResponse = response.sortedByDescending { it.stars }
        return sortedResponse.take(3)
    }
}