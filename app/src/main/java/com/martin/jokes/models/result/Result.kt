package com.martin.jokes.models.result


sealed class Result<T>(open var data: T? = null) {

    data class Success<T>(override var data: T?) : Result<T>(data)

    data class Error<T>(
        override var data: T? = null,
        var throwable: Throwable? = null
    ) : Result<T>(data)

    data class Loading<T>(override var data: T? = null) : Result<T>(data)

    data class Empty<T>(override var data: T? = null) : Result<T>(data)

    fun dataOr(default: T): T {
        return data ?: default
    }

}
