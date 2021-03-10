package com.martin.jokes.vm.main

import androidx.hilt.lifecycle.ViewModelInject
import com.martin.jokes.models.Joke
import com.martin.jokes.models.result.CallResult
import com.martin.jokes.repos.main.MainRepository
import com.martin.jokes.vm.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

@Singleton
class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) :
	BaseViewModel() {

	var jokes = MutableStateFlow<CallResult<MutableList<Joke>>>(CallResult.Empty())

	init {
		getJokes()
	}

	private fun getJokes() {
		load(mainRepository::tenRandomJokes)
			.into(jokes)
			.start()
	}

}