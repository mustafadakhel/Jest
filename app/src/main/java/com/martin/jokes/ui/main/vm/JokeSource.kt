package com.martin.jokes.ui.main.vm

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.martin.jokes.api.JokesApi
import com.martin.jokes.models.Joke
import kotlinx.parcelize.Parcelize

class JokeSource(
	private val mainRepository: JokesApi
) : PagingSource<Int, Joke>() {

	private val colors = listOf(
		0xff353747, 0xffd3a9ea, 0xffdddae1, 0xff0a6354, 0xffff986d
	).map {
		val textColor = if (it == 0xff353747 || it == 0xff0a6354)
			Color.White.copy(alpha = 0.75f)
		else Color.Black.copy(alpha = 0.75f)
		val backgroundColor = Color(it)
		ColorPair(backgroundColor, textColor)
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Joke> {
		return try {
			val nextPage = params.key ?: 1
			val jokesListResponse = mainRepository.getTenRandomJokes()

			jokesListResponse.onEachIndexed { index, joke ->
				if (joke.colorPair.backgroundColor == Color(0)) {
					val randomColor = getRandomColorPairNoRepeat(jokesListResponse, index)
					joke.colorPair = randomColor
				}
			}

			LoadResult.Page(
				data = jokesListResponse,
				prevKey = if (nextPage == 1) null else nextPage - 1,
				nextKey = nextPage.plus(1)
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}

	private fun getRandomColorPairNoRepeat(jokesList: MutableList<Joke>, index: Int): ColorPair {
		val previousItem = jokesList.getOrNull(index - 1)
		val previousPreviousItem = jokesList.getOrNull(index - 2)
		val randomColor = getRandomColorPair()
		return checkIfNotSame(randomColor, previousItem, previousPreviousItem)
	}

	private fun checkIfNotSame(
		randomColor: ColorPair,
		previousItem: Joke?,
		previousPreviousItem: Joke?
	): ColorPair {
		val sameAsLastTwo = sameColors(randomColor, previousItem?.colorPair)
				|| sameColors(randomColor, previousPreviousItem?.colorPair)
		return if (sameAsLastTwo) {
			val newRandom = getRandomColorPair()
			checkIfNotSame(newRandom, previousItem, previousPreviousItem)
		} else randomColor
	}

	private fun sameColors(randomColor: ColorPair, colorPair: ColorPair?): Boolean {
		val firstColor = randomColor.backgroundColor.toString()
		val secondColor = colorPair?.backgroundColor?.toString()
		val same = firstColor == secondColor
		return same
	}

	private fun getRandomColorPair(): ColorPair {
		return colors.random()
	}

	override fun getRefreshKey(state: PagingState<Int, Joke>): Int? = state.anchorPosition
}

@Parcelize
data class ColorPair(
	val backgroundColor: Color = Color(0),
	val textColor: Color = Color(0)
) : Parcelable