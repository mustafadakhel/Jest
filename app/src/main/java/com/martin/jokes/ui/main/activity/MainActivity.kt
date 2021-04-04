package com.martin.jokes.ui.main.activity

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martin.jokes.models.Joke
import com.martin.jokes.ui.base.activity.BaseActivity
import com.martin.jokes.ui.main.layouts.JokeDetails
import com.martin.jokes.ui.main.layouts.MainPage
import com.martin.jokes.ui.main.vm.MainViewModel
import com.martin.jokes.ui.navigation.NavRoutes
import com.martin.jokes.ui.navigation.jokeArgs
import com.martin.jokes.utils.extensions.vm
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

	private lateinit var navController: NavHostController
	override val viewModel by vm(MainViewModel::class)

	@Composable
	override fun Create() {
		navController = rememberNavController()
		NavHost(navController, startDestination = NavRoutes.main) {
			composable(NavRoutes.main) {
				MainPage(viewModel, navController)
			}
			composable(route = NavRoutes.details, arguments = jokeArgs) {
				JokeDetails(Joke.fromArgs(it.arguments))
			}
		}
	}
}
