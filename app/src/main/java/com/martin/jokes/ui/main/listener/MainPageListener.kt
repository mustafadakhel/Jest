package com.martin.jokes.ui.main.listener

import com.martin.jokes.models.Joke
import com.martin.jokes.ui.base.listener.BaseListener

interface MainPageListener : BaseListener {
	fun onJokeClicked(joke: Joke)
}