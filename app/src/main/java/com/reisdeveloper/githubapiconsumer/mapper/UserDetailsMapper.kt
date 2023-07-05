package com.reisdeveloper.githubapiconsumer.mapper

import com.reisdeveloper.githubapiconsumer.uiModel.UserDetailsUiModel
import com.reisdeveloper.lib.domainModel.UserDetailsDomainModel

fun UserDetailsDomainModel.toUserDetailsUiModel() = UserDetailsUiModel(
    avatarUrl, htmlUrl, id, login, name, url, repos, followers, following
)