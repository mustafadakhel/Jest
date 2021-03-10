package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.martin.jokes.models.result.CallResult

@Composable
fun <T> Status(status: CallResult<T>, Success: @Composable () -> Unit) {
	Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
		when (status) {
			is CallResult.Empty -> Empty()
			is CallResult.Loading -> CircularProgressIndicator()
			is CallResult.Success -> Success()
			is CallResult.Error -> Error()
		}
	}
}