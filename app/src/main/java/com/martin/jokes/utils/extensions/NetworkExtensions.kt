package com.martin.jokes.utils.extensions

import com.martin.jokes.models.result.CallResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.supervisorScope

fun <T> T?.mapToResult(): CallResult<T> {
	return this?.let { safeData: T ->
		if (safeData is Collection<*> && safeData.isEmpty())
			CallResult.empty(safeData)
		else
			CallResult.success(safeData)
	} ?: run {
		CallResult.failure(throwable = Throwable("null response"))
	}
}

suspend fun <T> supervisedCall(block: suspend CoroutineScope.() -> CallResult<T>): CallResult<T> {
	var result: CallResult<T> = CallResult.loading()
	runCatching {
		supervisorScope(block)
	}.onFailure {
		result = CallResult.failure(throwable = it)
	}.onSuccess {
		result = it
	}
	return result
}
