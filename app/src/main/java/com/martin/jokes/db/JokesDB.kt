package com.martin.jokes.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.martin.jokes.db.dao.JokesDao
import com.martin.jokes.db.dao.RemoteKeysDao

const val JOKES_DB = "jokes.db"


abstract class JokesDB : RoomDatabase() {
	abstract val remoteKeysDao: RemoteKeysDao

	abstract val jokesDao: JokesDao

}

