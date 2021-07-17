package com.martin.jokes.repos.main

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import androidx.paging.*
import com.martin.jokes.api.JokesApi
import com.martin.jokes.db.JokesDB
import com.martin.jokes.models.Joke
import com.martin.jokes.ui.main.vm.JokeMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.parcelize.Parcelize
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

const val DEFAULT_PAGE_SIZE = 10

@Singleton
class MainRepository @Inject constructor(
	private val jokesApi: JokesApi,
	private val jokesDB: JokesDB
) {

	private val colors = listOf(
		0xff353747, 0xffd3a9ea, 0xffdddae1, 0xff0a6354, 0xffff986d
	).map {
		val textColor = if (it == 0xff353747 || it == 0xff0a6354)
			Color.White.copy(alpha = 0.75f)
		else Color.Black.copy(alpha = 0.75f)
		val backgroundColor = Color(it)
		ColorPair(backgroundColor, textColor)
	}

	@OptIn(ExperimentalPagingApi::class)
	fun letTheJokesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Joke>> {
		val pagingSourceFactory = {
			jokesDB.jokesDao.getAllTheJokesPaged()
		}
		return Pager(
			config = pagingConfig,
			pagingSourceFactory = pagingSourceFactory,
			remoteMediator = JokeMediator(jokesApi, jokesDB),
		).flow.addColors()
	}

	private fun Flow<PagingData<Joke>>.addColors(): Flow<PagingData<Joke>> {
		return map {
			it.map { joke ->
				if (joke.colorPair.backgroundColor == Color(0)) {
					val randomColor = getRandomColorPair(joke)
					joke.colorPair = randomColor
				}
				joke
			}
		}
	}

	private fun getRandomColorPair(joke: Joke): ColorPair {
		return colors.random(Random(joke.id))
	}

}


fun getDefaultPageConfig(): PagingConfig {
	return PagingConfig(
		pageSize = DEFAULT_PAGE_SIZE,
		enablePlaceholders = true,
		prefetchDistance = DEFAULT_PAGE_SIZE,
		initialLoadSize = DEFAULT_PAGE_SIZE
	)
}

@Parcelize
data class ColorPair(
	val backgroundColor: Color = Color(0),
	val textColor: Color = Color(0)
) : Parcelable
