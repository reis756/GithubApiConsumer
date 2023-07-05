package com.reisdeveloper.lib.mapper

import com.reisdeveloper.data.model.UserDetailsResponse
import com.reisdeveloper.lib.domainModel.UserDetailsDomainModel

fun UserDetailsResponse.toUserDetailsDomainModel() = UserDetailsDomainModel(
    avatarUrl, htmlUrl, id, login, name, url, repos, followers, following
)