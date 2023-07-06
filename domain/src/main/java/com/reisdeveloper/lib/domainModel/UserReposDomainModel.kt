package com.reisdeveloper.lib.domainModel

data class UserReposDomainModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String? = "",
    val forksCount: Int,
    val language: String? = "",
    val stargazersCount: Int,
    val htmlUrl: String,
    val url: String
)
