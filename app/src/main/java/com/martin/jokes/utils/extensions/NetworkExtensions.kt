package com.martin.jokes.utils.extensions

import com.martin.jokes.models.result.CallResult

fun <T> T?.mapToResult(): CallResult<T> {
    return this?.let { safeData: T ->
        if (safeData is Collection<*> && safeData.isEmpty())
            CallResult.Empty(safeData)
        else
            CallResult.Success(safeData)
    } ?: run {
        CallResult.Error(throwable = Throwable("null response"))
    }
}