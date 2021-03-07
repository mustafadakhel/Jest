package com.martin.jokes.utils.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.martin.jokes.models.base.Result
import com.martin.jokes.utils.LiveStatus
import com.martin.jokes.utils.ResultState

operator fun <B, T : List<B>?> ResultState<T?>.get(index: Int): B? {
    return value.data?.get(index)
}

operator fun <B, T : List<B>?> LiveData<T?>.get(index: Int): B? {
    return value?.get(index)
}

fun <T> ResultState<T>.setEmpty(): ResultState<T> {
    value = Result.Empty()
    return this
}

fun <T> ResultState<T>.setLoading(data: T? = null): ResultState<T> {
    value = Result.Loading(data ?: value.data)
    return this
}

fun <T> ResultState<T>.setSuccess(data: T? = null): ResultState<T> {
    value = Result.Success(data ?: value.data)
    return this
}

fun <T> ResultState<T>.setError(t: Throwable): ResultState<T> {
    value = Result.Error(throwable = t)
    return this
}

fun LiveStatus.postError() {
    postValue(Result.Status.ERROR)
}

fun LiveStatus.postLoading() {
    postValue(Result.Status.LOADING)
}

fun LiveStatus.postSuccess() {
    postValue(Result.Status.SUCCESS)
}

fun LiveStatus.postEmpty() {
    postValue(Result.Status.EMPTY)
}

fun <T> MutableLiveData<T>.notifyChanged() {
    value = value
}