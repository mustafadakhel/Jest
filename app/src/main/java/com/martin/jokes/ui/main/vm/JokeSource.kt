package com.martin.jokes.ui.main.vm

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.martin.jokes.models.Joke
import com.martin.jokes.repos.main.MainRepository

class JokeSource(
	private val mainRepository: MainRepository
) : PagingSource<Int, Joke>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Joke> {
		return try {
			val nextPage = params.key ?: 1
			val movieListResponse = mainRepository.tenRandomJokes()
			LoadResult.Page(
				data = movieListResponse.getOr(mutableListOf()),
				prevKey = if (nextPage == 1) null else nextPage - 1,
				nextKey = nextPage.plus(1)
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, Joke>): Int? = state.anchorPosition
}