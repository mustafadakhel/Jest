package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.martin.jokes.models.result.Result

@Composable
fun <T> Status(status: Result<T>, success: @Composable () -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        when (status) {
            is Result.Loading -> CircularProgressIndicator()
            is Result.Success -> success()
        }
    }
}