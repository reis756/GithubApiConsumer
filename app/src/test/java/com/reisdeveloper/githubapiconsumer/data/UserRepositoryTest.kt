package com.reisdeveloper.githubapiconsumer.data

import com.reisdeveloper.githubapiconsumer.FakeUserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private lateinit var repository: FakeUserRepository

    @Before
    fun setup() {
        repository = FakeUserRepository()
    }


    @Test
    fun get_user_details_by_name_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.getUserDetails("")

        assertEquals(repository.userDetailMock, result)
    }

    @Test
    fun repository_get_user_details_by_name_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.getUserDetails("")

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }

    @Test
    fun get_users_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.getUsers(0)

        assertEquals(listOf(repository.userMock), result)
    }

    @Test
    fun get_users_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.getUsers(0)

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }

    @Test
    fun get_user_repos_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.getUserRepos("")

        assertEquals(listOf(repository.userReposMock), result)
    }

    @Test
    fun get_user_repos_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.getUserRepos("")

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }
}