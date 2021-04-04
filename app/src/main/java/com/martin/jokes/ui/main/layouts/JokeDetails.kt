package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
		modifier = Modifier.padding(24.dp),
		elevation = 4.dp,
		backgroundColor = joke.colorPair.backgroundColor,
		shape = RoundedCornerShape(8.dp)
	) {
		Column() {
			Text(
				text = joke.type,
				modifier = Modifier.weight(0.25f)
					.padding(start = 40.dp, end = 40.dp, top = 80.dp),
				textAlign = TextAlign.Start,
				fontSize = 16.sp,
				color = joke.colorPair.textColor.copy(alpha = 0.5f)
			)
			Text(
				text = joke.setup,
				modifier = Modifier.weight(0.5f)
					.padding(horizontal = 40.dp),
				textAlign = TextAlign.Start,
				fontSize = 32.sp,
				color = joke.colorPair.textColor
			)
			Text(
				text = joke.punchline,
				modifier = Modifier.weight(0.25f)
					.padding(start = 56.dp, end = 56.dp, bottom = 40.dp),
				textAlign = TextAlign.Start,
				fontSize = 24.sp,
				color = joke.colorPair.textColor
			)
		}
	}
}