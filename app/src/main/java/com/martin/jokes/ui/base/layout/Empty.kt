package com.martin.jokes.ui.base.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun Empty() {
	Column(horizontalAlignment = Alignment.CenterHorizontally) {
		Icon(imageVector = Icons.Rounded.Info, contentDescription = "")
		Text(text = "Nothing to show")
	}
}