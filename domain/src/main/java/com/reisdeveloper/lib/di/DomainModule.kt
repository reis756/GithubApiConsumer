package com.reisdeveloper.lib.di

import com.reisdeveloper.lib.usecase.GetUserByNameUseCase
import com.reisdeveloper.lib.usecase.GetUserReposUseCase
import com.reisdeveloper.lib.usecase.GetUsersUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetUsersUseCase(get()) }
    single { GetUserByNameUseCase(get()) }
    single { GetUserReposUseCase(get()) }
}