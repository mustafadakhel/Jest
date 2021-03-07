package com.martin.jokes.utils

import androidx.lifecycle.MutableLiveData
import com.martin.jokes.models.base.Result

class LiveStatus(status: Result.Status) : MutableLiveData<Result.Status>() {
    init {
        value = status
    }
}