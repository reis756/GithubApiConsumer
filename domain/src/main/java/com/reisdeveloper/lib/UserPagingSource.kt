package com.reisdeveloper.lib

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.reisdeveloper.data.model.UserResponse
import com.reisdeveloper.data.repository.UserRepository
import kotlin.math.max

const val STARTING_KEY = 0

class UserPagingSource(
    private val userRepository: UserRepository
) : PagingSource<Int, UserResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> {
        val pageIndex = params.key ?: STARTING_KEY

        val range = pageIndex.until(pageIndex + params.loadSize)
        return try {
            val response = userRepository.getUsers(pageIndex)

            LoadResult.Page(
                data = response,
                prevKey = when (pageIndex) {
                    STARTING_KEY -> null
                    else -> when (val prevKey = ensureValidKey(key = range.first - params.loadSize)) {
                        STARTING_KEY -> null
                        else -> prevKey
                    }
                },
                nextKey = range.last + 1
            )
        } catch (exception: Throwable) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}