package com.martin.jokes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.martin.jokes.db.dao.JokesDao
import com.martin.jokes.db.dao.RemoteKeysDao
import com.martin.jokes.models.Joke
import com.martin.jokes.models.RemoteKeys

const val JOKES_DB = "jokes.db"

@Database(entities = [Joke::class, RemoteKeys::class], exportSchema = false, version = 1)
abstract class JokesDB : RoomDatabase() {
	abstract val remoteKeysDao: RemoteKeysDao

	abstract val jokesDao: JokesDao

}

