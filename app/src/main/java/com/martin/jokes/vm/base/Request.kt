package com.martin.jokes.vm.base

import androidx.compose.runtime.MutableState
import com.martin.jokes.models.base.Result

class Request<T> {
    var request: (suspend () -> Result<T>)? = null
    var consumer: MutableState<Result<T>>? = null

    fun into(consumer: MutableState<Result<T>>): Request<T> {
        this.consumer = consumer
        return this
    }

    fun load(call: suspend () -> Result<T>): Request<T> {
        request = call
        return this
    }
}