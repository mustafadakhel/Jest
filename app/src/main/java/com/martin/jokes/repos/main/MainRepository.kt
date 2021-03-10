package com.martin.jokes.repos.main

import com.martin.jokes.api.JokesApi
import com.martin.jokes.models.Joke
import com.martin.jokes.models.result.CallResult
import com.martin.jokes.utils.extensions.mapToResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
	private val jokesApi: JokesApi,
) {
	suspend fun tenRandomJokes(): CallResult<MutableList<Joke>> {
		return supervisedCall {
			jokesApi
				.getTenRandomJokes()
				.mapToResult()
		}
	}
}

suspend fun <T> supervisedCall(block: suspend CoroutineScope.() -> CallResult<T>): CallResult<T> {
	var result: CallResult<T> = CallResult.Loading()
	runCatching {
		withContext(IO + SupervisorJob()) {
			block()
		}
	}.onFailure {
		result = CallResult.Error(throwable = it)
	}.onSuccess {
		result = it
	}
	return result
}
