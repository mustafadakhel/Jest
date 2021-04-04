package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.martin.jokes.models.Joke
import com.martin.jokes.ui.base.layout.Error
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JokesList(
	jokesFlow: Flow<PagingData<Joke>>,
	onJokeClicked: (joke: Joke) -> Unit
) {
	val jokes = jokesFlow.collectAsLazyPagingItems()
	LazyVerticalGrid(
		cells = GridCells.Fixed(2)
	) {
		items(count = jokes.itemCount, itemContent = { index ->
			val joke = jokes[index] ?: return@items
			JokeItem(
				joke = joke,
				onJokeClicked = onJokeClicked
			)
		})
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