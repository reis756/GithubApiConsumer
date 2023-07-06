package com.reisdeveloper.githubapiconsumer.mapper

import com.reisdeveloper.githubapiconsumer.uiModel.UserReposUiModel
import com.reisdeveloper.lib.domainModel.UserReposDomainModel

fun UserReposDomainModel.toUserReposUiModel() = UserReposUiModel(
    id, name, fullName, description, forksCount, language, stargazersCount, htmlUrl, url
)