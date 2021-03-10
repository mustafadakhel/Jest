package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.martin.jokes.models.result.Result
import com.martin.jokes.ui.main.MainPageListener
import com.martin.jokes.vm.main.MainViewModel

@Composable
fun MainPage(viewModel: MainViewModel, listener: MainPageListener) {
    MaterialTheme() {
        Status(status = viewModel.jokes.value) {
            JokesList(
                modifier = Modifier.fillMaxSize(),
                jokes = viewModel.jokes.value.dataOr(mutableListOf()),
                onJokeClicked = listener::onJokeClicked
            )
        }
    }
}
