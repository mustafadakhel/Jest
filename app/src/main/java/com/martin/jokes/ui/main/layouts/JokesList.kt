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
import com.martin.jokes.ui.base.layout.Error
import kotlinx.coroutines.flow.Flow

@Composable
fun JokesList(
	jokesFlow: Flow<PagingData<Joke>>,
	onJokeClicked: (joke: Joke) -> Unit
) {
	val jokes = jokesFlow.collectAsLazyPagingItems()
	LazyColumn(
		contentPadding = PaddingValues(8.dp, 8.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		items(jokes) { joke ->
			JokeItem(
				joke = joke ?: Joke(),
				onJokeClicked = onJokeClicked
			)
		}
		jokes.apply {
			when {
				loadState.refresh is LoadState.Loading -> {
					item {
						Box(
							contentAlignment = Alignment.Center,
							modifier = Modifier.fillMaxSize()
						) {
							CircularProgressIndicator(modifier = Modifier.wrapContentSize())
						}
					}
				}
				loadState.append is LoadState.Loading -> {
					item {
						Box(
							contentAlignment = Alignment.Center,
							modifier = Modifier.fillMaxSize()
						) {
							CircularProgressIndicator(modifier = Modifier.wrapContentSize())
						}
					}
				}
				loadState.refresh is LoadState.Error -> {
					item {
						Box(
							contentAlignment = Alignment.Center,
							modifier = Modifier.fillMaxSize()
						) {
							Error {
								retry()
							}
						}
					}
				}
				loadState.append is LoadState.Error -> {
					item {
						Box(
							contentAlignment = Alignment.Center,
							modifier = Modifier.fillMaxSize()
						) {
							Error {
								retry()
							}
						}
					}
				}
			}
		}
	}
}