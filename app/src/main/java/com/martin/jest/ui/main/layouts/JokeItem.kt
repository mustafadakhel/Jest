package com.martin.jest.ui.main.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import com.martin.jest.models.Joke

@Composable
fun JokeItem(
	joke: Joke,
	onJokeClicked: (joke: Joke) -> Unit
) {
	Card(
		modifier = Modifier
			.wrapContentHeight()
			.fillMaxWidth()
			.padding(16.dp, 8.dp)
			.clickable {
				onJokeClicked(joke)
			},
		shape = RoundedCornerShape(4.dp),
		backgroundColor = joke.colorPair.backgroundColor,
		elevation = 0.dp
	) {
		Column(modifier = Modifier.padding(12.dp)) {
			Text(
				text = joke.type,
				modifier = Modifier.padding(top = 16.dp, start = 16.dp),
				textAlign = TextAlign.Start,
				fontSize = 12.sp,
				color = joke.colorPair.textColor.copy(alpha = 0.5f)
			)
			Text(
				text = joke.setup,
				modifier = Modifier.padding(
					top = 8.dp,
					bottom = 32.dp,
					end = 16.dp,
					start = 16.dp
				),
				textAlign = TextAlign.Start,
				fontSize = 18.sp,
				color = joke.colorPair.textColor
			)
		}
	}
}