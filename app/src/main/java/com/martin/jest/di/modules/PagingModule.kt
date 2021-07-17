package com.martin.jest.di.modules

import androidx.paging.ExperimentalPagingApi
import com.martin.jest.api.JokesApi
import com.martin.jest.db.JokesDB
import com.martin.jest.ui.main.vm.JokesMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [DBModule::class, RemoteSourceModule::class])
class PagingModule {

	@OptIn(ExperimentalPagingApi::class)
	@Singleton
	@Provides
	fun provideJokesMediator(jokesApi: JokesApi, jokesDB: JokesDB): JokesMediator {
		return JokesMediator(jokesApi, jokesDB)
	}

}