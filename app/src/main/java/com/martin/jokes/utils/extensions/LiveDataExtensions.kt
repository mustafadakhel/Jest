package com.martin.jokes.utils.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.martin.jokes.models.base.Result
import com.martin.jokes.utils.BaseLiveData
import com.martin.jokes.utils.LiveStatus

operator fun <B, T : List<B>?> BaseLiveData<T?>.get(index: Int): B? {
    return value?.data?.get(index)
}

operator fun <B, T : List<B>?> LiveData<T?>.get(index: Int): B? {
    return value?.get(index)
}

fun <T> BaseLiveData<T>.setEmpty(): BaseLiveData<T> {
    postValue(Result.empty())
    return this
}

fun <T> BaseLiveData<T>.setLoading(data: T? = null): BaseLiveData<T> {
    postValue(Result.loading(data ?: value?.data))
    return this
}

fun <T> BaseLiveData<T>.setSuccess(data: T? = null): BaseLiveData<T> {
    postValue(Result.success(data ?: value?.data))
    return this
}

fun <T> BaseLiveData<T>.setError(t: Throwable): BaseLiveData<T> {
    postValue(Result.error(t))
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