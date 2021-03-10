package com.martin.jokes.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.martin.jokes.models.Joke

@Composable
fun JokesList(
    modifier: Modifier = Modifier,
    jokes: List<Joke>,
    onJokeClicked: (joke: Joke) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp, 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(jokes) { joke ->
            JokeItem(
                joke = joke,
                onJokeClicked = onJokeClicked
            )
        }
    }
}