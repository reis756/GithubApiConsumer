package com.reisdeveloper.githubapiconsumer.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.reisdeveloper.githubapiconsumer.base.BaseViewModel
import com.reisdeveloper.githubapiconsumer.mapper.toUserDetailsUiModel
import com.reisdeveloper.githubapiconsumer.mapper.toUserUiModel
import com.reisdeveloper.githubapiconsumer.uiModel.UserDetailsUiModel
import com.reisdeveloper.lib.usecase.GetUserByNameUseCase
import com.reisdeveloper.lib.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map

class HomeViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserByNameUseCase: GetUserByNameUseCase
) : BaseViewModel() {

    private val _screen = MutableSharedFlow<Screen>()
    val screen: SharedFlow<Screen> = _screen

    fun getUsers() =
        getUsersUseCase.execute().map { paging ->
            paging.map { it.toUserUiModel() }
        }.cachedIn(viewModelScope)

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

    sealed class Screen {
        data class Loading(val loading: Boolean) : Screen()
        data class UserByName(val user: UserDetailsUiModel) : Screen()
        object UserByNameError : Screen()
    }
}