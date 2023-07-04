package com.reisdeveloper.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)