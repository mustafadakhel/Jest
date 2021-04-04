package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.martin.jokes.models.Joke

@Composable
fun JokeItem(
    joke: Joke,
    onJokeClicked: (joke: Joke) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onJokeClicked(joke)
            },
        elevation = 4.dp
    ) {
        Text(
            text = joke.setup,
            modifier = Modifier.fillMaxWidth().padding(16.dp, 16.dp),
            textAlign = TextAlign.Center
        )
    }
}