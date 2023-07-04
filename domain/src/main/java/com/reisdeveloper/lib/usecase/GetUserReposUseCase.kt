package com.reisdeveloper.lib.usecase

import com.reisdeveloper.data.model.UserReposResponse
import com.reisdeveloper.data.repository.UserRepository

class GetUserReposUseCase(
    private val userRepository: UserRepository
) : AbstractUseCase<String, List<UserReposResponse>>() {

    override suspend fun execute(param: String): List<UserReposResponse> {
        return userRepository.getUserRepos(param)
    }
}