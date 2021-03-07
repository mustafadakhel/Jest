package com.martin.jokes.ui.activities.main

import android.os.Bundle
import com.martin.jokes.R
import com.martin.jokes.databinding.ActivityMainBinding
import com.martin.jokes.models.Joke
import com.martin.jokes.ui.activities.base.BaseActivity
import com.martin.jokes.ui.navigators.main.MainActivityNavigator
import com.martin.jokes.utils.extensions.showToast
import com.martin.jokes.vm.main.MainViewModel
import com.martin.simplerecycler.BaseAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainActivityNavigator, MainViewModel>(R.layout.activity_main),
    MainActivityNavigator, JokeItemsListener {

    override fun getNavigator() = this

    override fun getViewModelClass() = MainViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainRecycler.setItemsListener(this)
    }

    override fun customOnClick() {
        showToast(R.string.click)
    }

    override fun onReload() {
        viewModel.getJokes()
    }
}

interface JokeItemsListener : BaseAdapter.BaseItemsListener<Joke> {
    fun customOnClick()
}
