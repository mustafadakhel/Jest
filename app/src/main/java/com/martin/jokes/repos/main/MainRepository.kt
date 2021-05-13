package com.martin.jokes.repos.main

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.martin.jokes.api.JokesApi
import com.martin.jokes.models.Joke
import com.martin.jokes.models.result.CallResult
import com.martin.jokes.ui.main.vm.JokeSource
import com.martin.jokes.utils.extensions.mapToResult
import com.martin.jokes.utils.extensions.supervisedCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

const val DEFAULT_PAGE_SIZE = 10

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

	fun letJokesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Joke>> {
		return Pager(
			config = pagingConfig,
			pagingSourceFactory = { JokeSource(jokesApi) }
		).flow
	}
}

fun getDefaultPageConfig(): PagingConfig {
	return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
}