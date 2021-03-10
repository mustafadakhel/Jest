package com.martin.jokes.ui.main.vm

import androidx.hilt.lifecycle.ViewModelInject
import com.martin.jokes.models.Joke
import com.martin.jokes.models.result.CallResult
import com.martin.jokes.repos.main.MainRepository
import com.martin.jokes.ui.base.vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

@Singleton
class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) :
	BaseViewModel() {

	var jokes = MutableStateFlow<CallResult<MutableList<Joke>>>(CallResult.Empty())

	init {
		getJokes()
	}

	fun getJokes() {
		load(mainRepository::tenRandomJokes)
			.into(jokes)
			.start()
	}

}