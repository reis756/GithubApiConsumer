package com.reisdeveloper.lib.usecase

import com.reisdeveloper.lib.ResultWrapper
import com.reisdeveloper.lib.asFailure
import com.reisdeveloper.lib.asSuccess
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

abstract class AbstractUseCase<in PARAM, out RESPONSE> constructor(
    private val key: String? = null
) {

    protected abstract suspend fun execute(param: PARAM): RESPONSE

    open suspend fun onError(throwable: Throwable): ResultWrapper.Failure = throwable.asFailure()

    @Suppress("TooGenericExceptionCaught")
    open operator fun invoke(
        value: PARAM,
        forceLoad: Boolean? = false,
        keyAdditionalCache: String? = "",
    ): Flow<ResultWrapper<RESPONSE>> = flow {
        emit(ResultWrapper.Loading)
        try {
            val result = withContext(IO) {
                execute(value)
            }
            emit(result.asSuccess())
        } catch (e: Throwable) {
            emit(onError(e))
        } finally {
            emit(ResultWrapper.DismissLoading)
        }
    }
}