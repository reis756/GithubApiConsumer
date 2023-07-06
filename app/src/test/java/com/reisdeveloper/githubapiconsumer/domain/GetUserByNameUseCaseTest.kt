package com.reisdeveloper.githubapiconsumer.domain

import app.cash.turbine.test
import com.reisdeveloper.githubapiconsumer.FakeUserRepository
import com.reisdeveloper.githubapiconsumer.MainCoroutineRule
import com.reisdeveloper.lib.Error
import com.reisdeveloper.lib.ResultWrapper
import com.reisdeveloper.lib.mapper.toUserDetailsDomainModel
import com.reisdeveloper.lib.usecase.GetUserByNameUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetUserByNameUseCaseTest {

    private lateinit var repository: FakeUserRepository

    private lateinit var getUserByNameUseCase: GetUserByNameUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repository = FakeUserRepository()
        getUserByNameUseCase = GetUserByNameUseCase(repository)
    }


    @Test
    fun get_user_details_by_name_success() = runBlocking {
        repository.setReturnError(false)

        val result = getUserByNameUseCase.invoke("test")

        result.test {
            assertEquals(ResultWrapper.Loading, awaitItem())
            assertEquals(
                ResultWrapper.Success(repository.userDetailMock.toUserDetailsDomainModel()),
                awaitItem()
            )
            assertEquals(ResultWrapper.DismissLoading, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun get_user_details_by_name_error() = runBlocking {
        repository.setReturnError(true)

        val result = getUserByNameUseCase.invoke("test")

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