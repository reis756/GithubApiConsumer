package com.reisdeveloper.lib

typealias OnErrorBaseViewModel = suspend (error: Error) -> Unit
typealias OnSuccessBaseViewModel<T> = suspend (value: T) -> Unit
typealias OnCompletionBaseViewModel = suspend () -> Unit
typealias OnLoadingBaseViewModel = suspend (Boolean) -> Unit