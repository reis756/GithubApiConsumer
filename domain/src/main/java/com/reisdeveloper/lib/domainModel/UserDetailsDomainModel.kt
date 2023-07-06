package com.reisdeveloper.lib.domainModel

data class UserDetailsDomainModel(
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
