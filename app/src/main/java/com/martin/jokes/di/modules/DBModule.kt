package com.martin.jokes.di.modules

import android.content.Context
import androidx.room.Room
import com.martin.jokes.db.JOKES_DB
import com.martin.jokes.db.JokesDB
import com.martin.jokes.db.dao.JokesDao
import com.martin.jokes.db.dao.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
	@Singleton
	@Provides
	internal fun provideDatabase(context: Context): JokesDB {
		return Room.databaseBuilder(context, JokesDB::class.java, JOKES_DB).build()
	}

	@Singleton
	@Provides
	internal fun provideJokesDao(jokesDB: JokesDB): JokesDao {
		return jokesDB.jokesDao
	}

	@Singleton
	@Provides
	internal fun provideRemoteKeysDao(jokesDB: JokesDB): RemoteKeysDao {
		return jokesDB.remoteKeysDao
	}
}