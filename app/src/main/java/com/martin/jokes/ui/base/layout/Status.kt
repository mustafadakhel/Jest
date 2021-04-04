package com.martin.jokes.ui.base.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.martin.jokes.models.result.CallResult
import com.martin.jokes.ui.base.listener.BaseListener

@Composable
fun <T> Status(status: CallResult<T>, listener: BaseListener, Success: @Composable () -> Unit) {
	Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
		when {
			status.isEmpty -> Empty()
			status.isLoading -> CircularProgressIndicator()
			status.isSuccess -> Success()
			status.isFailure -> Error(listener::onReload)
		}
	}
}