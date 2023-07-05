package com.reisdeveloper.lib.usecase

import com.reisdeveloper.data.repository.UserRepository
import com.reisdeveloper.lib.domainModel.UserReposDomainModel
import com.reisdeveloper.lib.mapper.toUserReposDomainModel

class GetUserReposUseCase(
    private val userRepository: UserRepository
) : AbstractUseCase<String, List<UserReposDomainModel>>() {

    override suspend fun execute(param: String): List<UserReposDomainModel> {
        return userRepository.getUserRepos(param).map { it.toUserReposDomainModel() }
    }
}