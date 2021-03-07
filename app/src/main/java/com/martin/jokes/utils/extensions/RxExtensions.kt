package com.martin.jokes.utils.extensions

import com.martin.jokes.models.base.Result
import hu.akarnokd.rxjava2.schedulers.SharedScheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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

fun <T> Single<T>.subscribeOnIO(): Single<T> {
    return subscribeOn(SharedScheduler(Schedulers.io()))
}

fun <T> Single<T>.observeOnMain(): Single<T> {
    return observeOn(AndroidSchedulers.mainThread())
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable?): Disposable =
    apply { compositeDisposable?.add(this) }
