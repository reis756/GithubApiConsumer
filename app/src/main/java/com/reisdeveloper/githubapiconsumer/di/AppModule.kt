package com.reisdeveloper.githubapiconsumer.di

import com.reisdeveloper.githubapiconsumer.di.ui.home.HomeViewModel
import com.reisdeveloper.githubapiconsumer.di.ui.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(),get()) }
    viewModel { UserViewModel(get(), get()) }
}