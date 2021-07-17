package com.martin.jokes.utils.extensions

import androidx.paging.PagingState
import com.martin.jokes.models.result.CallResult
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> mutableStateFlow(): MutableStateFlow<CallResult<T>> {
	return MutableStateFlow(CallResult.empty())
}

val <T> MutableStateFlow<CallResult<T>>?.isLoading: Boolean
	get() = this?.value?.isLoading ?: false


fun <Key : Any, Value : Any> PagingState<Key, Value>.closestItemToAnchor(): Value? {
	return anchorPosition?.let { position ->
		closestItemToPosition(position)
	}
}
