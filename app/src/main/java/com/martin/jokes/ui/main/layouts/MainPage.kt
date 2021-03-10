package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.martin.jokes.ui.main.MainPageListener
import com.martin.jokes.vm.main.MainViewModel

@Composable
fun MainPage(viewModel: MainViewModel, listener: MainPageListener) {
	val jokes = viewModel.jokes.collectAsState()
	MaterialTheme() {
		Status(status = jokes.value) {
			JokesList(
				modifier = Modifier.fillMaxSize(),
				jokes = jokes.value.dataOr(mutableListOf()),
				onJokeClicked = listener::onJokeClicked
			)
		}
	}
}
