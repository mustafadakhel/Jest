package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
fun JokeItem(
	joke: Joke,
	onJokeClicked: (joke: Joke) -> Unit
) {
	Card(
		modifier = Modifier.fillMaxSize().padding(8.dp)
			.clickable {
				onJokeClicked(joke)
			},
		shape = RoundedCornerShape(4.dp),
		elevation = 0.dp
	) {
		Text(
			text = joke.setup,
			modifier = Modifier.fillMaxSize().background(joke.colorPair.backgroundColor)
				.padding(top = 42.dp, bottom = 72.dp, end = 22.dp, start = 22.dp),
			textAlign = TextAlign.Start,
			fontSize = 18.sp,
			color = joke.colorPair.textColor
		)
		Text(
			text = joke.type,
			modifier = Modifier.fillMaxWidth().wrapContentHeight()
				.padding(top = 16.dp, start = 16.dp),
			textAlign = TextAlign.Start,
			fontSize = 12.sp,
			color = joke.colorPair.textColor.copy(alpha = 0.5f)
		)
	}
}