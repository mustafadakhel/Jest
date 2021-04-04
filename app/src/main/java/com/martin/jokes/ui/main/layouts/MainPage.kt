package com.martin.jokes.ui.main.layouts

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.martin.jokes.ui.main.vm.MainViewModel

@Composable
fun MainPage(viewModel: MainViewModel, navController: NavController) {
	val jokesFlow = viewModel.pagedJokes
	JokesList(
		jokesFlow = jokesFlow,
		onJokeClicked = {
			navController.navigate("jokes/${it.setup}/${it.punchline}/${it.type}")
		}
	)
}
