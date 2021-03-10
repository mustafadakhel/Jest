package com.martin.jokes.utils.extensions

import com.martin.jokes.models.result.Result

fun <T> T?.mapToResult(): Result<T> {
    return this?.let { safeData: T ->
        if (safeData is Collection<*> && safeData.isEmpty())
            Result.Empty(safeData)
        else
            Result.Success(safeData)
    } ?: run {
        Result.Error(throwable = Throwable("null response"))
    }
}