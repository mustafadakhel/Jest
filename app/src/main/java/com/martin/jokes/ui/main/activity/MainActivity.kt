package com.martin.jokes.ui.main.activity

import androidx.compose.runtime.Composable
import com.martin.jokes.R
import com.martin.jokes.models.Joke
import com.martin.jokes.ui.base.activity.BaseActivity
import com.martin.jokes.ui.main.layouts.MainPage
import com.martin.jokes.ui.main.listener.MainPageListener
import com.martin.jokes.ui.main.vm.MainViewModel
import com.martin.jokes.utils.extensions.showToast
import com.martin.jokes.utils.extensions.vm
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), MainPageListener {

	override val viewModel by vm(MainViewModel::class)

	@Composable
	override fun Create() {
		MainPage(viewModel, this)
	}

	override fun onJokeClicked(joke: Joke) {
		showToast(R.string.click)
	}

	override fun onReload() {
//		viewModel.getJokes()
	}
}