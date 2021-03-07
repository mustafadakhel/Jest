package com.martin.jokes.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.martin.jokes.ui.base.BaseActivity
import com.martin.jokes.utils.extensions.showToast
import com.martin.jokes.vm.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {

    override fun getViewModelClass() = MainViewModel::class

    @Composable
    override fun Create() {
        MaterialTheme() {
            JokesList(
                modifier = Modifier.fillMaxSize(),
                jokes = viewModel.jokes.value.dataOr(mutableListOf())
            ) {
                showToast("click")
            }
        }
    }
}