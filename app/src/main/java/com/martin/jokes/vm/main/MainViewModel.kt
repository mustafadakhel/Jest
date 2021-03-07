package com.martin.jokes.vm.main

import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import com.martin.jokes.models.Joke
import com.martin.jokes.models.base.Result
import com.martin.jokes.repos.main.MainRepository
import com.martin.jokes.vm.base.BaseViewModel

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {

    val jokes = mutableStateOf<Result<MutableList<Joke>>>(Result.Empty())

    init {
        getJokes()
    }

    private fun getJokes() {
        load(mainRepository::tenRandomJokes).into(jokes).start()
    }

}