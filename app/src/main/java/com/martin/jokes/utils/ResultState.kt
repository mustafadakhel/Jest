package com.martin.jokes.utils

import androidx.compose.runtime.MutableState
import com.martin.jokes.models.base.Result

class ResultState<T>(var initData: T?, override var value: Result<T>) : MutableState<Result<T>> {

    var data: T? = null
        get() = value.data
        set(newValue) {
            value.data = newValue
            field = newValue
        }

    override fun component1(): Result<T> {
        return value
    }

    override fun component2(): (Result<T>) -> Unit {
        return { value }
    }

}