package com.martin.jest.ui.main.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.martin.jest.models.Joke
import com.martin.jest.repos.main.MainRepository
import com.martin.jest.ui.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(mainRepository: MainRepository) :
	BaseViewModel() {

	val pagedJokes: Flow<PagingData<Joke>> by lazy {
		mainRepository.letTheJokesFlow().cachedIn(viewModelScope)
	}

}