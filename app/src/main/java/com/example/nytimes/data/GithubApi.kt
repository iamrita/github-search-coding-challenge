package com.example.nytimes.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/users/{username}/repos")
    suspend fun getRepositoriesFromUsername(@Path("username") username: String) : List<Repository>

    data class RepositoryList (@SerializedName("") val repositories: List<Repository>)

    data class Repository(
        @SerializedName("id") val id : Int,
        @SerializedName("name") val name: String
    )

}