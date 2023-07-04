package com.reisdeveloper.githubapiconsumer.di

import com.reisdeveloper.githubapiconsumer.ui.home.HomeViewModel
import com.reisdeveloper.githubapiconsumer.ui.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(),get()) }
    viewModel { UserViewModel(get(), get()) }
}