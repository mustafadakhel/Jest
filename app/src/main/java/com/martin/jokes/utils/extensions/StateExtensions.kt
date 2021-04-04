package com.martin.jokes.utils.extensions

import com.martin.jokes.models.result.CallResult
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> mutableStateFlow(): MutableStateFlow<CallResult<T>> {
	return MutableStateFlow(CallResult.empty())
}

val <T> MutableStateFlow<CallResult<T>>?.isLoading: Boolean
	get() = this?.value?.isLoading ?: false

