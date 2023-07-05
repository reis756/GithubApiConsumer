package com.reisdeveloper.githubapiconsumer.ui.user

import com.reisdeveloper.data.model.UserDetailsResponse
import com.reisdeveloper.data.model.UserReposResponse
import com.reisdeveloper.githubapiconsumer.base.BaseViewModel
import com.reisdeveloper.lib.usecase.GetUserByNameUseCase
import com.reisdeveloper.lib.usecase.GetUserReposUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class UserViewModel(
    private val getUserByNameUseCase: GetUserByNameUseCase,
    private val getUserReposUseCase: GetUserReposUseCase
) : BaseViewModel() {

    private val _screen = MutableSharedFlow<Screen>()
    val screen: SharedFlow<Screen> = _screen

    fun getUserByName(name: String) {
        getUserByNameUseCase(name).singleExec(
            onLoadingBaseViewModel = {
                _screen.emit(Screen.Loading(it))
            },
            onError = {
                _screen.emit(Screen.UserByNameError)
            },
            onSuccessBaseViewModel = { userDetails ->
                _screen.emit(Screen.UserByName(userDetails))
            }
        )
    }

    fun getUserRepos(name: String) {
        getUserReposUseCase(name).singleExec(
            onLoadingBaseViewModel = {
                _screen.emit(Screen.Loading(it))
            },
            onError = {
                _screen.emit(Screen.UserReposError)
            },
            onSuccessBaseViewModel = { repos ->
                _screen.emit(Screen.UserRepos(repos))
            }
        )
    }

    sealed class Screen {
        data class Loading(val loading: Boolean) : Screen()
        data class UserByName(val user: UserDetailsResponse) : Screen()
        object UserByNameError : Screen()
        data class UserRepos(val repos: List<UserReposResponse>) : Screen()
        object UserReposError : Screen()
    }
}