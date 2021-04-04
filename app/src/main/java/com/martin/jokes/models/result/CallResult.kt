package com.martin.jokes.models.result

import java.io.Serializable

open class CallResult<T>(
	var data: Any?
) : Serializable {

	val isEmpty: Boolean get() = data is Empty<*>
	val isLoading: Boolean get() = data is Loading<*>
	val isSuccess: Boolean get() = data is Success<*>
	val isFailure: Boolean get() = data is Failure

	fun getOrNull(): T? =
		when {
			isFailure || isEmpty -> null
			data is Success<*> -> (data as? Success<*>)?.data as? T?
			data is Loading<*> -> (data as? Loading<*>)?.data as? T?
			else -> null
		}

	fun getOr(data: T): T = getOrNull() ?: data

	override fun toString(): String =
		when (data) {
			isSuccess -> "Success($data)"
			isLoading -> "Loading($data)"
			isFailure -> "Failure($data)"
			else -> "Empty($data)"
		}


	companion object {
		fun <T> empty(data: T? = null): CallResult<T> =
			CallResult(Empty(data))

		fun <T> loading(data: T? = null): CallResult<T> =
			CallResult(Loading(data))

		fun <T> success(data: T): CallResult<T> =
			CallResult(Success(data))

		fun <T> failure(throwable: Throwable): CallResult<T> =
			CallResult(Failure(throwable))
	}

	class Empty<T>(newData: T?) : CallResult<T?>(newData)

	class Loading<T>(newData: T?) : CallResult<T?>(newData)

	class Success<T>(newData: T?) : CallResult<T?>(newData)

	class Failure(throwable: Throwable) : CallResult<Throwable>(throwable)
}