package com.martin.jest.ui.main.layouts

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavController
import com.martin.jest.ui.main.vm.MainViewModel

@Composable
fun MainPage(viewModel: MainViewModel, navController: NavController) {
	JokesList(
		jokes = viewModel.pagedJokes
	) {
		navController.navigate("jest/${it.setup}/${it.punchline}/${it.type}/${it.colorPair.textColor.toArgb()}/${it.colorPair.backgroundColor.toArgb()}")
	}
}
