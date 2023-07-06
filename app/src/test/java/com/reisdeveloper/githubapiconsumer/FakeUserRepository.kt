package com.reisdeveloper.githubapiconsumer

import com.reisdeveloper.data.model.UserDetailsResponse
import com.reisdeveloper.data.model.UserReposResponse
import com.reisdeveloper.data.model.UserResponse
import com.reisdeveloper.data.repository.UserRepository

class FakeUserRepository : UserRepository {

    val userMock = UserResponse("", "", 0, "","", "")
    val userDetailMock = UserDetailsResponse("", "", 0, "","", "", "", "", "")
    val userReposMock = UserReposResponse(0, "", "", "",0, "", 0, "", "")

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getUsers(since: Int?): List<UserResponse> {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return listOf(userMock)
    }

    override suspend fun getUserDetails(user: String): UserDetailsResponse {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return userDetailMock
    }

    override suspend fun getUserRepos(user: String): List<UserReposResponse> {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return listOf(userReposMock)
    }
}