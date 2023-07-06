package com.reisdeveloper.githubapiconsumer.domain

import app.cash.turbine.test
import com.reisdeveloper.githubapiconsumer.FakeUserRepository
import com.reisdeveloper.githubapiconsumer.MainCoroutineRule
import com.reisdeveloper.lib.Error
import com.reisdeveloper.lib.ResultWrapper
import com.reisdeveloper.lib.mapper.toUserReposDomainModel
import com.reisdeveloper.lib.usecase.GetUserReposUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetUserReposUseCaseTest {
    private lateinit var repository: FakeUserRepository

    private lateinit var getUserReposUseCase: GetUserReposUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repository = FakeUserRepository()
        getUserReposUseCase = GetUserReposUseCase(repository)
    }


    @Test
    fun get_user_repos_by_name_success() = runBlocking {
        repository.setReturnError(false)

        val result = getUserReposUseCase.invoke("test")

        result.test {
            assertEquals(ResultWrapper.Loading, awaitItem())
            assertEquals(
                ResultWrapper.Success(listOf(repository.userReposMock.toUserReposDomainModel())),
                awaitItem()
            )
            assertEquals(ResultWrapper.DismissLoading, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun get_user_repos_by_name_error() = runBlocking {
        repository.setReturnError(true)

        val result = getUserReposUseCase.invoke("test")
        result.test {
            try {
                assertEquals(ResultWrapper.Loading, awaitItem())
                assertEquals(
                    ResultWrapper.Failure(Error.UnknownException(Throwable("Test exception"))),
                    awaitItem()
                )
                cancelAndConsumeRemainingEvents()
                awaitComplete()

            } catch (e: Throwable) {
                assertEquals(ResultWrapper.DismissLoading, awaitItem())
                awaitComplete()
            }
        }
    }
}