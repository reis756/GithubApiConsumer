package com.reisdeveloper.lib.usecase

import com.reisdeveloper.data.model.UserDetailsResponse
import com.reisdeveloper.data.repository.UserRepository

class GetUserByNameUseCase(
    private val userRepository: UserRepository
) : AbstractUseCase<String, UserDetailsResponse>() {

    override suspend fun execute(param: String): UserDetailsResponse {
        return userRepository.getUserDetails(param)
    }
}