package com.martin.jokes.utils.extensions

import androidx.compose.runtime.MutableState
import com.martin.jokes.models.result.CallResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun <T> MutableState<CallResult<T>>.setLoading() {

}
//
//fun <T> MutableState<CallResult<T>>.setError(t: Throwable) {
//    if (!tryEmit(CallResult.Error(throwable = t, data = value.data)))
//        CoroutineScope(Dispatchers.Main).launch {
//            emit(CallResult.Error(throwable = t, data = value.data))
//        }
//}
//
//fun <T> MutableState<CallResult<T>>.update(new: CallResult<T>) {
//    if (!tryEmit(new))
//        CoroutineScope(Dispatchers.Main).launch {
//            emit(new)
//        }
//}
