package com.example.nytimes.data

import android.os.Parcelable
import com.example.nytimes.model.Repository
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users/{org}/repos")
    suspend fun getRepositoriesFromOrgName(@Path("org") orgName: String) : List<Repository>

}