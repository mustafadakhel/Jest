package com.martin.jokes.utils

import androidx.compose.runtime.MutableState
import com.martin.jokes.models.result.Result

class ResultState<T>(override var value: Result<T>) : MutableState<Result<T>> {

    override fun component1(): Result<T> {
        return value
    }

    override fun component2(): (Result<T>) -> Unit {
        return { value }
    }

}