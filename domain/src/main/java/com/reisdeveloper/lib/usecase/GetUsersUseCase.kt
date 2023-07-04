package com.reisdeveloper.lib.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.reisdeveloper.data.model.UserResponse
import com.reisdeveloper.data.repository.UserRepository
import com.reisdeveloper.lib.UserPagingSource
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(
    private val userRepository: UserRepository
) {
    fun execute(): Flow<PagingData<UserResponse>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
            pagingSourceFactory = { UserPagingSource(userRepository) }
        ).flow
    }

    companion object {
        const val ITEMS_PER_PAGE = 30
    }
}