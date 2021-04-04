package com.martin.jokes.ui.main.layouts

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.martin.jokes.ui.main.listener.MainPageListener
import com.martin.jokes.ui.main.vm.MainViewModel

@Composable
fun MainPage(viewModel: MainViewModel, listener: MainPageListener) {
//	val jokes = viewModel.jokes.collectAsState().value
	val jokesFlow = viewModel.pagedJokes
//	val scrollState = viewModel.scrollState
	MaterialTheme() {
//		Status(status = pagedJokes, listener = listener) {
			JokesList(
				jokesFlow = jokesFlow,
				onJokeClicked = listener::onJokeClicked
			)
//		}
	}
}
