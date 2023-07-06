package com.reisdeveloper.githubapiconsumer.ui.user

import com.reisdeveloper.githubapiconsumer.base.BaseViewModel
import com.reisdeveloper.githubapiconsumer.mapper.toUserDetailsUiModel
import com.reisdeveloper.githubapiconsumer.mapper.toUserReposUiModel
import com.reisdeveloper.githubapiconsumer.uiModel.UserDetailsUiModel
import com.reisdeveloper.githubapiconsumer.uiModel.UserReposUiModel
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
                _screen.emit(Screen.UserByName(userDetails.toUserDetailsUiModel()))
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
                _screen.emit(Screen.UserRepos(repos.map { it.toUserReposUiModel() }))
            }
        )
    }

    sealed class Screen {
        data class Loading(val loading: Boolean) : Screen()
        data class UserByName(val user: UserDetailsUiModel) : Screen()
        object UserByNameError : Screen()
        data class UserRepos(val repos: List<UserReposUiModel>) : Screen()
        object UserReposError : Screen()
    }
}