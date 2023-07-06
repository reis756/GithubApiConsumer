package com.reisdeveloper.data.di

import com.reisdeveloper.data.api.GithubApi
import com.reisdeveloper.data.repository.UserRepository
import com.reisdeveloper.data.repository.UserRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<GithubApi> { GithubApi.create() }

    single<UserRepository> { UserRepositoryImpl(get()) }
}