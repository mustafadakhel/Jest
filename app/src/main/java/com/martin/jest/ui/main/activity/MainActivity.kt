package com.martin.jest.ui.main.activity

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martin.jest.models.Joke
import com.martin.jest.ui.base.activity.BaseActivity
import com.martin.jest.ui.main.layouts.JokeDetails
import com.martin.jest.ui.main.layouts.MainPage
import com.martin.jest.ui.main.vm.MainViewModel
import com.martin.jest.ui.navigation.NavRoutes
import com.martin.jest.ui.navigation.jokeArgs
import com.martin.jest.utils.extensions.vm
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
