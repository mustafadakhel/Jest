package com.martin.jokes.utils

import com.martin.jokes.models.result.CallResult
import kotlinx.coroutines.flow.MutableStateFlow

class Request<T> {
	var request: (suspend () -> CallResult<T>)? = null
	var consumer: MutableStateFlow<CallResult<T>>? = null

	fun into(consumer: MutableStateFlow<CallResult<T>>): Request<T> {
		consumer.tryEmit(CallResult.Loading())
		this.consumer = consumer
		return this
	}

	fun load(call: suspend () -> CallResult<T>): Request<T> {
		request = call
		return this
	}
}
