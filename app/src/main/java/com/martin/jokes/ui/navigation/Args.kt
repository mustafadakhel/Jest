package com.martin.jokes.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

val jokeArgs: List<NamedNavArgument>
	get() = listOf(navArgument("setup") {
		type = NavType.StringType
	}, navArgument("punch") {
		type = NavType.StringType
	}, navArgument("type") {
		type = NavType.StringType
	})
