package com.martin.jokes.api

import io.reactivex.Flowable

interface Call<in Param, Output> {
    fun data(param: Param): Flowable<Output>
    suspend fun refresh(param: Param)
}