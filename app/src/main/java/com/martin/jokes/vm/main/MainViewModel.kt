package com.martin.jokes.vm.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.martin.jokes.models.Joke
import com.martin.jokes.repos.main.MainRepository
import com.martin.jokes.ui.navigators.main.MainActivityNavigator
import com.martin.jokes.utils.BaseLiveData
import com.martin.jokes.vm.base.BaseViewModel
import com.martin.jokes.vm.base.disposeOnExit
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) :
    BaseViewModel<MainActivityNavigator>() {

    val jokes = BaseLiveData<MutableList<Joke>>()

    init {
        getJokes()
    }

    fun getJokes() {
        viewModelScope.launch {
            mainRepository.getTenRandomJokes().consumeAndDispose(jokes)
        }.disposeOnExit()
    }
}