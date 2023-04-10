package com.example.nytimes.model

import com.google.gson.annotations.SerializedName

/**
 * [Repository] is a data class representing a repository object. This class only contains
 * information about the repository that is relevant for this app's purpose.
 *
 * id: unique id of repository
 * fullName: full name of the repository, including what organization it is in
 * stars: how many people have starred this repository
 * url: link to repository
 */
data class Repository(
    @SerializedName("id") val id : Int,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("html_url") val url: String
)