package com.reisdeveloper.githubapiconsumer.di.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.reisdeveloper.data.model.UserDetailsResponse
import com.reisdeveloper.githubapiconsumer.base.BaseViewModel
import com.reisdeveloper.lib.usecase.GetUserByNameUseCase
import com.reisdeveloper.lib.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class HomeViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserByNameUseCase: GetUserByNameUseCase
) : BaseViewModel() {

    private val _searchedUser = MutableLiveData<UserDetailsResponse>()
    val searchedUser: LiveData<UserDetailsResponse> = _searchedUser

    private val _screen = MutableSharedFlow<Screen>()
    val screen: SharedFlow<Screen> = _screen

    fun getUsers() =
        getUsersUseCase.execute().cachedIn(viewModelScope)

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

    sealed class Screen {
        data class Loading(val loading: Boolean) : Screen()
        data class UserByName(val user: UserDetailsResponse) : Screen()
        object UserByNameError : Screen()
    }
}