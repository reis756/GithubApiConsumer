package com.reisdeveloper.data.model

import com.google.gson.annotations.SerializedName

data class UserReposResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("description") val description: String,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("language") val language: String,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("url") val url: String
)