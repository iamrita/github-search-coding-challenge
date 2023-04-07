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
    suspend fun getRepositoriesFromUsername(@Path("username") username: String) : Response<RepositoryList>

    @JsonClass(generateAdapter = true)
    data class RepositoryList (val repositories: List<Repository>)

    @JsonClass(generateAdapter = true)
    data class Repository(
        @Json(name = "id") val id : Int,
        @Json(name = "name") val name: String
    )

}