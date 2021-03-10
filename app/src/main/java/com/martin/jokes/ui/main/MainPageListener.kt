package com.martin.jokes.ui.main

import com.martin.jokes.models.Joke

interface MainPageListener {
    fun onJokeClicked(joke: Joke)
}