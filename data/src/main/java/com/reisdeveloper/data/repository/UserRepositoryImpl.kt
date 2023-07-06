package com.reisdeveloper.data.repository

import com.reisdeveloper.data.api.GithubApi
import com.reisdeveloper.data.model.UserDetailsResponse
import com.reisdeveloper.data.model.UserReposResponse
import com.reisdeveloper.data.model.UserResponse

class UserRepositoryImpl(
    private val githubApi: GithubApi
) : UserRepository{

    override suspend fun getUsers(since: Int?): List<UserResponse> {
        return githubApi.getUsers(since)
    }

    override suspend fun getUserDetails(user: String): UserDetailsResponse {
        return githubApi.getUserDetails(user)
    }

    override suspend fun getUserRepos(user: String): List<UserReposResponse> {
        return githubApi.getUserRepos(user)
    }
}