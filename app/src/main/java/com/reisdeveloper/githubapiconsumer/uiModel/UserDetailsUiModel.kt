package com.reisdeveloper.githubapiconsumer.uiModel

data class UserDetailsUiModel(
    val avatarUrl: String,
    val htmlUrl: String,
    val id: Int,
    val login: String,
    val name: String,
    val url: String,
    val repos: String,
    val followers: String,
    val following: String
)
