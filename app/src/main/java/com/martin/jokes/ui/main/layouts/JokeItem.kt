package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
        Column {
            Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
            Text(
                text = joke.toString(),
                modifier = Modifier.fillMaxWidth(0.9f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
        }
    }
}