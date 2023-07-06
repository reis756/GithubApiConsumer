package com.reisdeveloper.githubapiconsumer.domain

import com.reisdeveloper.githubapiconsumer.FakeUserRepository
import com.reisdeveloper.githubapiconsumer.MainCoroutineRule
import com.reisdeveloper.lib.usecase.GetUsersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUsersUseCaseTest {

    private lateinit var repository: FakeUserRepository

    private lateinit var getUsersUseCase: GetUsersUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repository = FakeUserRepository()
        getUsersUseCase = GetUsersUseCase(repository)
    }


    @Test
    fun get_users_success() = runBlocking {
        repository.setReturnError(false)

        val result = getUsersUseCase.execute()

        //TODO implement assert
    }
}