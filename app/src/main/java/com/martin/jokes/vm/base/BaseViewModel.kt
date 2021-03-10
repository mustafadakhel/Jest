package com.martin.jokes.vm.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.jokes.models.result.CallResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseViewModel : ViewModel() {

	val isLoading = ObservableBoolean(false)

	var jobs: MutableList<Job>? = mutableListOf()

	override fun onCleared() {
		jobs?.forEach { it.cancel() }
		jobs = null
		super.onCleared()
	}

	fun <T> Request<T>.start() {
		setLoading()
		viewModelScope.launch(Dispatchers.IO) {
			request
				?.invoke()
				?.putInto(consumer)
			setLoading(false)
		}
	}

	fun <T> load(call: suspend () -> CallResult<T>): Request<T> {
		return Request<T>().load(call)
	}

	protected fun setLoading(loading: Boolean = true) {
		isLoading.set(loading)
	}

	private fun <T> CallResult<T>?.putInto(consumer: MutableStateFlow<CallResult<T>>?) {
		this?.let {
			consumer?.tryEmit(it)
		}
	}
}

