package com.martin.jokes.ui.main.layouts

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavController
import com.martin.jokes.ui.main.vm.MainViewModel

@Composable
fun MainPage(viewModel: MainViewModel, navController: NavController) {
	JokesList(
		jokes = viewModel.pagedJokes
	) {
		navController.navigate("jokes/${it.setup}/${it.punchline}/${it.type}/${it.colorPair.textColor.toArgb()}/${it.colorPair.backgroundColor.toArgb()}")
	}
}
