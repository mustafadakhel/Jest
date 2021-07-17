package com.martin.jokes.ui.main.vm

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.martin.jokes.api.JokesApi
import com.martin.jokes.db.JokesDB
import com.martin.jokes.models.Joke
import com.martin.jokes.models.RemoteKeys
import java.util.*

const val DEFAULT_PAGE_INDEX = 1

@ExperimentalPagingApi
class JokeMediator(private val jokesApi: JokesApi, private val jokesDB: JokesDB) :
	RemoteMediator<Int, Joke>() {

	override suspend fun load(
		loadType: LoadType, state: PagingState<Int, Joke>
	): MediatorResult {
		Log.i("MartinDakhel", "load")
		val page = getKeyPageData(loadType, state) ?: run {
			return MediatorResult.Success(true)
		}
		return try {
			val jokesListResponse = jokesApi.getTenRandomJokes().setAddedDate()
			jokesDB.withTransaction {
				if (loadType == LoadType.REFRESH) {
					jokesDB.remoteKeysDao.clearRemoteKeys()
					jokesDB.jokesDao.clearAllJokes()
				}
				val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
				val nextKey = page + 1
				val keys = jokesListResponse.map {
					RemoteKeys(jokeId = it.id, prevKey = prevKey, nextKey = nextKey)
				}
				jokesDB.remoteKeysDao.insertAll(keys)
				jokesDB.jokesDao.insertAll(jokesListResponse)
			}
			MediatorResult.Success(false)
		} catch (e: Exception) {
			return MediatorResult.Error(e)
		}
	}

	private fun List<Joke>.setAddedDate(): List<Joke> {
		return onEach {
			val rightNow: Calendar = Calendar.getInstance()
			it.added = rightNow.timeInMillis
		}
	}

	private suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, Joke>): Int? {
		return when (loadType) {
			LoadType.REFRESH -> {
				val remoteKeys = getClosestRemoteKey(state)
				remoteKeys?.nextKey?.minus(1) ?: 0
			}
			LoadType.PREPEND -> null
			LoadType.APPEND -> {
				getLastRemoteKey(state)?.nextKey
			}
		}
	}

	private suspend fun getLastRemoteKey(state: PagingState<Int, Joke>): RemoteKeys? {
		return state.lastItemOrNull()?.let { joke ->
			jokesDB.withTransaction {
				jokesDB.remoteKeysDao.remoteKeysByJokeId(joke.id)
			}
		}
	}

	private suspend fun getClosestRemoteKey(state: PagingState<Int, Joke>): RemoteKeys? {
		return state.anchorPosition?.let { position ->
			state.closestItemToPosition(position)?.id?.let { repoId ->
				jokesDB.withTransaction {
					jokesDB.remoteKeysDao.remoteKeysByJokeId(repoId)
				}
			}
		}
	}

}