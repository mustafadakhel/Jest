package com.martin.jokes.vm.base

import androidx.compose.runtime.MutableState
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.jokes.models.base.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        consumer?.value = Result.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            request
                ?.invoke()
                ?.putInto(consumer)
            setLoading(false)
        }
    }

    fun <T> load(call: suspend () -> Result<T>): Request<T> {
        return Request<T>().load(call)
    }

    protected fun setLoading(loading: Boolean = true) {
        isLoading.set(loading)
    }

    private fun <T> Result<T>?.putInto(consumer: MutableState<Result<T>>?) {
        this?.let { consumer?.value = it }
    }
}

