package com.reisdeveloper.lib.mapper

import com.reisdeveloper.data.model.UserResponse
import com.reisdeveloper.lib.domainModel.UserDomainModel

fun UserResponse.toDomainModel() = UserDomainModel(
    avatarUrl, htmlUrl, id, login, type, url
)