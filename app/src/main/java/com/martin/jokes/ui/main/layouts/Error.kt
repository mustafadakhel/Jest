package com.martin.jokes.ui.main.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun Error() {
	Column(horizontalAlignment = Alignment.CenterHorizontally) {
		Icon(imageVector = Icons.Rounded.Warning, contentDescription = "")
		Text(text = "Something went wrong")
	}
}