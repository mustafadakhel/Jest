package com.martin.jest.ui.navigation

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
	}, navArgument("textColor") {
		type = NavType.IntType
	}, navArgument("backgroundColor") {
		type = NavType.IntType
	})
