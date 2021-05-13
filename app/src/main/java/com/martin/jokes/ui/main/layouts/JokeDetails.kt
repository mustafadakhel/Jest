package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.layout.*
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
	Box(modifier = Modifier.fillMaxWidth().requiredHeight(420.dp)) {
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
						.padding(start = 20.dp, end = 20.dp, top = 40.dp),
					textAlign = TextAlign.Start,
					fontSize = 16.sp,
					color = joke.colorPair.textColor.copy(alpha = 0.5f)
				)
				Text(
					text = joke.setup,
					modifier = Modifier.weight(0.6f)
						.padding(horizontal = 20.dp),
					textAlign = TextAlign.Start,
					fontSize = 32.sp,
					color = joke.colorPair.textColor
				)
				Text(
					text = joke.punchline,
					modifier = Modifier.weight(0.35f)
						.padding(start = 28.dp, end = 28.dp, bottom = 20.dp),
					textAlign = TextAlign.Start,
					fontSize = 24.sp,
					color = joke.colorPair.textColor
				)
			}
		}
	}
}