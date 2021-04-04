package com.martin.jokes.ui.main.vm

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.martin.jokes.models.Joke
import com.martin.jokes.repos.main.MainRepository
import com.martin.jokes.ui.base.vm.BaseViewModel
import com.martin.jokes.utils.extensions.mutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) :
	BaseViewModel() {

	val pagedJokes: Flow<PagingData<Joke>> = Pager(PagingConfig(10)) {
		JokeSource(mainRepository)
	}.flow

	var jokes = mutableStateFlow<MutableList<Joke>>()

}