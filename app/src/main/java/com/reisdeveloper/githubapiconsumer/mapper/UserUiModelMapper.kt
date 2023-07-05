package com.reisdeveloper.githubapiconsumer.mapper

import com.reisdeveloper.githubapiconsumer.uiModel.UserUiModel
import com.reisdeveloper.lib.domainModel.UserDomainModel

fun UserDomainModel.toUserUiModel() = UserUiModel(
    avatarUrl, htmlUrl, id, login, type, url
)