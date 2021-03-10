package com.martin.jokes.ui.base.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.jokes.models.result.CallResult
import com.martin.jokes.utils.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseViewModel : ViewModel() {


	var jobs: MutableList<Job>? = mutableListOf()

	override fun onCleared() {
		jobs?.forEach { it.cancel() }
		jobs = null
		super.onCleared()
	}

	fun <T> Request<T>.start() {
		viewModelScope.launch(Dispatchers.IO) {
			request
				?.invoke()
				?.putInto(consumer)
		}
	}

	fun <T> load(call: suspend () -> CallResult<T>): Request<T> {
		return Request<T>().load(call)
	}

	private fun <T> CallResult<T>?.putInto(consumer: MutableStateFlow<CallResult<T>>?) {
		this?.let {
			consumer?.tryEmit(it)
		}
	}
}

