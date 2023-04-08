package com.example.nytimes.data

import com.example.nytimes.model.Repository
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users/{org}/repos")
    suspend fun getRepositoriesFromOrgName(@Path("org") orgName: String) : List<Repository>

}