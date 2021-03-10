package com.martin.jokes.ui.main.activity

import androidx.compose.runtime.Composable
import com.martin.jokes.R
import com.martin.jokes.models.Joke
import com.martin.jokes.ui.base.BaseActivity
import com.martin.jokes.ui.main.MainPageListener
import com.martin.jokes.ui.main.layouts.MainPage
import com.martin.jokes.utils.extensions.showToast
import com.martin.jokes.utils.extensions.vm
import com.martin.jokes.vm.main.MainViewModel
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
}