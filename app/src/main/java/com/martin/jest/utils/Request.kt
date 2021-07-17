package com.martin.jest.utils

import com.martin.jest.models.result.CallResult
import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("unused")
open class Request<T> {
	var request: (suspend () -> CallResult<T>)? = null
	var onSuccess: (suspend (data: CallResult<T>) -> Unit)? = null
	var consumer: MutableStateFlow<CallResult<T>>? = null

	open fun into(consumer: MutableStateFlow<CallResult<T>>): Request<T> {
		consumer.tryEmit(CallResult.loading(consumer.value.getOrNull()))
		this.consumer = consumer
		return this
	}

	fun watch(onSuccess: suspend (data: CallResult<T>) -> Unit): Request<T> {
		this.onSuccess = onSuccess
		return this
	}

	fun load(call: suspend () -> CallResult<T>): Request<T> {
		request = call
		return this
	}
}