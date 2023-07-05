package com.reisdeveloper.lib.usecase

import com.reisdeveloper.data.repository.UserRepository
import com.reisdeveloper.lib.domainModel.UserDetailsDomainModel
import com.reisdeveloper.lib.mapper.toUserDetailsDomainModel

class GetUserByNameUseCase(
    private val userRepository: UserRepository
) : AbstractUseCase<String, UserDetailsDomainModel>() {

    override suspend fun execute(param: String): UserDetailsDomainModel {
        return userRepository.getUserDetails(param).toUserDetailsDomainModel()
    }
}