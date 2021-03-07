package com.martin.jokes.utils

import androidx.lifecycle.MutableLiveData
import com.martin.jokes.models.base.Result

class BaseLiveData<T>(var initData: T?) : MutableLiveData<Result<T>>(
		if (initData == null)
			Result.empty<T>()
		else Result.success(initData)
) {

	var data: T? = null
		get() = value?.data
		set(newValue) {
			value?.data = newValue
			field = newValue
		}

	constructor() : this(null)

	override fun setValue(value: Result<T>?) {
		super.setValue(value)
		initData = value?.data
	}

	var status: Result.Status = Result.Status.EMPTY
		get() = value?.status ?: Result.Status.EMPTY
		set(newValue) {
			value?.status = newValue
			field = newValue
		}

	fun postBaseValue(value: T?) {
		if (value == null)
			postValue(Result.empty())
		else postValue(Result.success(value))
	}
}