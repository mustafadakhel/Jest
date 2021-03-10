package com.martin.jokes.repos.main

import com.martin.jokes.api.JokesApi
import com.martin.jokes.models.Joke
import com.martin.jokes.models.result.CallResult
import com.martin.jokes.utils.extensions.mapToResult
import com.martin.jokes.utils.extensions.supervisedCall
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
