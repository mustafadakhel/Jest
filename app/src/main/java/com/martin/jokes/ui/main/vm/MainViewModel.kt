package com.martin.jokes.ui.main.vm

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.martin.jokes.models.Joke
import com.martin.jokes.repos.main.MainRepository
import com.martin.jokes.ui.base.vm.BaseViewModel
import com.martin.jokes.utils.extensions.mutableStateFlow
import javax.inject.Singleton

@Singleton
class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) :
	BaseViewModel() {

	var jokes = mutableStateFlow<MutableList<Joke>>()

	init {
		getJokes()
	}

	fun getJokes() {
		load(mainRepository::tenRandomJokes)
			.into(jokes)
			.watch {
				Log.i(javaClass.simpleName, "success")
			}
			.start()
	}

}