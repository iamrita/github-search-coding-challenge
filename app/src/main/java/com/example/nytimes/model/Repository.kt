package com.example.nytimes.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("id") val id : Int,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("html_url") val url: String
)