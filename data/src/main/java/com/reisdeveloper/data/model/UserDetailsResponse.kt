package com.reisdeveloper.data.model

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("public_repos") val repos: String,
    @SerializedName("followers") val followers: String,
    @SerializedName("following") val following: String
)