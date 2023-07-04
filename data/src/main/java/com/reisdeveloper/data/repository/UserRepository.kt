package com.reisdeveloper.data.repository

import com.reisdeveloper.data.model.UserDetailsResponse
import com.reisdeveloper.data.model.UserReposResponse
import com.reisdeveloper.data.model.UserResponse

interface UserRepository {
    suspend fun getUsers(since: Int?): List<UserResponse>
    suspend fun getUserDetails(user: String): UserDetailsResponse
    suspend fun getUserRepos(user: String): List<UserReposResponse>
}