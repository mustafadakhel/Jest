package com.martin.jokes.repos.main

import com.martin.jokes.api.JokesApi
import com.martin.jokes.models.Joke
import com.martin.jokes.models.result.Result
import com.martin.jokes.utils.extensions.log
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
    suspend fun tenRandomJokes(): Result<MutableList<Joke>> {
        return supervisedCall {
            jokesApi
                .getTenRandomJokes()
                .mapToResult()
        }
    }
}

suspend fun <T> supervisedCall(block: suspend CoroutineScope.() -> Result<T>): Result<T> {
    return withContext(IO + SupervisorJob()) {
        runCatching { block() }.getOrElse {
            it.log()
            Result.Error(throwable = it)
        }
    }
}
