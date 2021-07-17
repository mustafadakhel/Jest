package com.martin.jest.ui.main.layouts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.martin.jest.models.Joke


@Composable
fun JokeDetails(joke: Joke) {
		Card(
			modifier = Modifier.padding(24.dp)
				.fillMaxWidth()
				.wrapContentHeight(),
			elevation = 4.dp,
			backgroundColor = joke.colorPair.backgroundColor,
			shape = RoundedCornerShape(8.dp)
		) {
			Column(
				modifier = Modifier.verticalScroll(rememberScrollState())
			) {
				Text(
					text = joke.type,
					modifier = Modifier
						.padding(start = 20.dp, end = 20.dp, top = 32.dp),
					textAlign = TextAlign.Start,
					fontSize = 16.sp,
					color = joke.colorPair.textColor.copy(alpha = 0.5f)
				)
				Text(
					text = joke.setup,
					modifier = Modifier
						.padding(horizontal = 20.dp,vertical = 24.dp),
					textAlign = TextAlign.Start,
					fontSize = 32.sp,
					color = joke.colorPair.textColor
				)
				Text(
					text = joke.punchline,
					modifier = Modifier
						.padding(start = 28.dp, end = 28.dp, bottom = 20.dp, top = 40.dp),
					textAlign = TextAlign.Start,
					fontSize = 24.sp,
					color = joke.colorPair.textColor
				)
			}
		}
}