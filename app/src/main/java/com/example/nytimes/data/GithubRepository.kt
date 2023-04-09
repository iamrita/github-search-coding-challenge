package com.example.nytimes.data

import android.util.Log
import com.example.nytimes.model.Repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * GithubRepository creates a wrapper around [GithubApiService] to get a list of repositories
 * in the function getRepositories. It sorts the response by the number of stars, and returns
 * the top three repositories for a given orgName.
 */
class GithubRepository {
    private val api = GithubApiService().api
    suspend fun getRepositories(orgName: String): List<Repository> {
        val response = api.getRepositoriesFromOrgName(orgName)
        val sortedResponse = response.sortedByDescending { it.stars }
        return sortedResponse.take(3)
    }
}