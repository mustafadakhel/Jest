package com.martin.jokes.utils.extensions

import com.martin.jokes.utils.ResultState

operator fun <B, T : List<B>?> ResultState<T?>.get(index: Int): B? {
    return value.data?.get(index)
}