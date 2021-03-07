package com.martin.jokes.vm.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.jokes.models.base.Result
import com.martin.jokes.ui.navigators.base.BaseNavigator
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseViewModel<N : BaseNavigator> : ViewModel() {

    val isLoading = ObservableBoolean(false)

    var jobs: MutableList<Job>? = mutableListOf()

    private var refNavigator: WeakReference<N>? = null

    private val requestsPool: MutableList<Int> = mutableListOf()

    var navigator: N
        get() = refNavigator?.get()!!
        set(navigator) {
            this.refNavigator = WeakReference(navigator)
        }

    override fun onCleared() {
        jobs?.forEach { it.cancel() }
        jobs = null
        refNavigator?.clear()
        refNavigator = null
        super.onCleared()
    }

    fun <T> Result<T>.consumeAndDispose(consumer: MutableLiveData<Result<T>>): Job {
        consumer.value = Result.loading(consumer.value?.data)
        return
            consumer.value = this@consumeAndDispose
        }.disposeOnExit()
    }

    fun Job.disposeOnExit(): Job {
        if (jobs == null)
            this.cancel()
        else
            jobs?.add(this)
        return this
    }

    private fun setLoading() {
        isLoading.set(true)
    }
}
