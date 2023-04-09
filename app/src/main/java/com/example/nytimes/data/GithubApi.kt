package com.example.nytimes.data

import com.example.nytimes.model.Repository
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * GithubApi defines the method to call the Github API, passing in a given orgName
 * to the correct path, and returning a list of [Repository] objects.
 */
interface GithubApi {
    @GET("/users/{org}/repos")
    suspend fun getRepositoriesFromOrgName(@Path("org") orgName: String) : List<Repository>
}