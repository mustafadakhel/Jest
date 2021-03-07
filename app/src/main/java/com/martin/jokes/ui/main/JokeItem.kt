package com.martin.jokes.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.martin.jokes.models.Joke

@Composable
fun JokeItem(
    modifier: Modifier,
    joke: Joke
) {
    Card(modifier = modifier, elevation = 4.dp) {
        Column() {
            Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
            Text(
                text = joke.toString(),
                modifier = modifier.fillMaxWidth(0.9f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
        }
    }
}