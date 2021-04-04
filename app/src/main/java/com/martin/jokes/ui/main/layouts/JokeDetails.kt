package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.martin.jokes.models.Joke


@Composable
fun JokeDetails(joke: Joke) {
	Card(
		modifier = Modifier.padding(16.dp),
		elevation = 4.dp
	) {
		Column() {
			Text(
				text = joke.setup,
				modifier = Modifier.weight(0.85f)
					.padding(24.dp),
				textAlign = TextAlign.Center,
				fontSize = 24.sp
			)
			Text(
				text = joke.punchline,
				modifier = Modifier.weight(0.15f)
					.padding(horizontal = 28.dp),
				textAlign = TextAlign.Center,
				fontSize = 18.sp
			)
		}
	}
}