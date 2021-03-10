package com.martin.jokes.models.result


sealed class CallResult<T>(open var data: T? = null) {

	data class Success<T>(override var data: T?) : CallResult<T>(data)

	data class Error<T>(
		override var data: T? = null,
		var throwable: Throwable? = null
	) : CallResult<T>(data)

	data class Loading<T>(override var data: T? = null) : CallResult<T>(data)

	data class Empty<T>(override var data: T? = null) : CallResult<T>(data)

	fun dataOr(default: T): T {
		return data ?: default
	}

	val isLoading
		get() = this is Loading

	val isEmpty
		get() = this is Empty

	val isError
		get() = this is Error

	val isSuccess
		get() = this is Success

}
