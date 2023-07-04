package com.reisdeveloper.githubapiconsumer.di.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reisdeveloper.data.model.UserDetailsResponse
import com.reisdeveloper.data.model.UserReposResponse
import com.reisdeveloper.githubapiconsumer.base.BaseViewModel
import com.reisdeveloper.lib.usecase.GetUserByNameUseCase
import com.reisdeveloper.lib.usecase.GetUserReposUseCase

class UserViewModel(
    private val getUserByNameUseCase: GetUserByNameUseCase,
    private val getUserReposUseCase: GetUserReposUseCase
) : BaseViewModel() {

    private val _userDetails = MutableLiveData<UserDetailsResponse>()
    val userDetails: LiveData<UserDetailsResponse> = _userDetails

    private val _userRepos = MutableLiveData<List<UserReposResponse>>()
    val userRepos: LiveData<List<UserReposResponse>> = _userRepos

    fun getUserByName(name: String) {
        getUserByNameUseCase(name).singleExec(
            onLoadingBaseViewModel = {

            },
            onError = {
                it
            },
            onSuccessBaseViewModel = { userDetails ->
                _userDetails.value = userDetails
            }
        )
    }

    fun getUserRepos(name: String) {
        getUserReposUseCase(name).singleExec(
            onLoadingBaseViewModel = {

            },
            onError = {
                it
            },
            onSuccessBaseViewModel = { repos ->
                _userRepos.value = repos
            }
        )
    }
}