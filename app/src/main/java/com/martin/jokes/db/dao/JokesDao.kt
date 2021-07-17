package com.martin.jokes.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.martin.jokes.models.Joke

@Dao
interface JokesDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(jokes: List<Joke>)

	@Query("SELECT * FROM joke ORDER BY added")
	fun getAllTheJokesPaged(): PagingSource<Int, Joke>

	@Query("DELETE FROM joke")
	suspend fun clearAllJokes()

}