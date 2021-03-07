package com.martin.jokes.models.base


class Result<T>(var status: Status, var data: T?, var throwable: Throwable?) {
    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(throwable: Throwable?): Result<T> {
            return Result(
                Status.ERROR,
                null,
                throwable
            )
        }

        fun <T> loading(data: T?): Result<T> {
            return Result(
                Status.LOADING,
                data,
                null
            )
        }

        fun <T> empty(data: T? = null): Result<T> {
            return Result(
                Status.EMPTY,
                data,
                null
            )
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        EMPTY
    }
}
