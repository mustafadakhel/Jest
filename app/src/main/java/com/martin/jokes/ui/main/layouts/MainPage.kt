package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.martin.jokes.ui.base.layout.Status
import com.martin.jokes.ui.main.listener.MainPageListener
import com.martin.jokes.ui.main.vm.MainViewModel

@Composable
fun MainPage(viewModel: MainViewModel, listener: MainPageListener) {
	val jokes = viewModel.jokes.collectAsState().value
	MaterialTheme() {
		Status(status = jokes, listener = listener) {
			JokesList(
				modifier = Modifier.fillMaxSize(),
				jokes = jokes.dataOr(mutableListOf()),
				onJokeClicked = listener::onJokeClicked
			)
		}
	}
}
