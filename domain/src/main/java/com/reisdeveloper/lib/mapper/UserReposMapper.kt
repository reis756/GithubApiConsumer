package com.reisdeveloper.lib.mapper

import com.reisdeveloper.data.model.UserReposResponse
import com.reisdeveloper.lib.domainModel.UserReposDomainModel

fun UserReposResponse.toUserReposDomainModel() = UserReposDomainModel(
    id, name, fullName, description, forksCount, language, stargazersCount, htmlUrl, url
)