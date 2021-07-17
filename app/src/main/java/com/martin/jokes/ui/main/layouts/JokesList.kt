package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.martin.jokes.models.Joke
import kotlinx.coroutines.flow.Flow

@Composable
fun JokesList(
	jokes: Flow<PagingData<Joke>>,
	onJokeClicked: (joke: Joke) -> Unit
) {
	val pagedJokes = jokes.collectAsLazyPagingItems()

	LazyColumn() {
		if (pagedJokes.loadState.refresh == LoadState.Loading) {
			item {
				Row(
					modifier = Modifier
						.wrapContentHeight()
						.fillMaxWidth()
						.padding(8.dp),
					horizontalArrangement = Arrangement.Center,
					verticalAlignment = Alignment.CenterVertically
				) {
					CircularProgressIndicator(
						modifier = Modifier.size(24.dp, 24.dp)
					)
				}
			}
		}

		items(lazyPagingItems = pagedJokes) { joke ->
			JokeItem(
				joke = joke ?: Joke(),
				onJokeClicked = onJokeClicked
			)
		}

		if (pagedJokes.loadState.append == LoadState.Loading) {
			item {
				Row(
					modifier = Modifier
						.wrapContentHeight()
						.fillMaxWidth()
						.padding(8.dp),
					horizontalArrangement = Arrangement.Center,
					verticalAlignment = Alignment.CenterVertically
				) {
					CircularProgressIndicator(
						modifier = Modifier.size(24.dp, 24.dp)
					)
				}
			}
		}
	}
}