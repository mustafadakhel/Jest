package com.martin.jokes.ui.main.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.martin.jokes.models.Joke
import com.martin.jokes.repos.main.MainRepository
import com.martin.jokes.ui.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(mainRepository: MainRepository) :
	BaseViewModel() {

	val pagedJokes: Flow<PagingData<Joke>> = mainRepository.letJokesFlow().cachedIn(viewModelScope)

}