package com.reisdeveloper.lib.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.reisdeveloper.data.repository.UserRepository
import com.reisdeveloper.lib.domainModel.UserDomainModel
import com.reisdeveloper.lib.mapper.toDomainModel
import kotlin.math.max

const val STARTING_KEY = 0

class UserPagingSource(
    private val userRepository: UserRepository
) : PagingSource<Int, UserDomainModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDomainModel> {
        val pageIndex = params.key ?: STARTING_KEY

        val range = pageIndex.until(pageIndex + params.loadSize)
        return try {
            val response = userRepository.getUsers(pageIndex)

            LoadResult.Page(
                data = response.map { it.toDomainModel() },
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

    override fun getRefreshKey(state: PagingState<Int, UserDomainModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}