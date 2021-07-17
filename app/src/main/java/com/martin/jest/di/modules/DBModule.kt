package com.martin.jest.di.modules

import android.app.Application
import androidx.room.Room
import com.martin.jest.db.JOKES_DB
import com.martin.jest.db.JokesDB
import com.martin.jest.db.dao.JokesDao
import com.martin.jest.db.dao.RemoteKeysDao
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
	internal fun provideDatabase(application: Application): JokesDB {
		return Room.databaseBuilder(application.applicationContext, JokesDB::class.java, JOKES_DB).build()
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