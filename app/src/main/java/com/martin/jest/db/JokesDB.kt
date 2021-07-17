package com.martin.jest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.martin.jest.db.dao.JokesDao
import com.martin.jest.db.dao.RemoteKeysDao
import com.martin.jest.models.Joke
import com.martin.jest.models.RemoteKeys

const val JOKES_DB = "jest.db"

@Database(entities = [Joke::class, RemoteKeys::class], exportSchema = false, version = 1)
abstract class JokesDB : RoomDatabase() {
	abstract val remoteKeysDao: RemoteKeysDao

	abstract val jokesDao: JokesDao

}

