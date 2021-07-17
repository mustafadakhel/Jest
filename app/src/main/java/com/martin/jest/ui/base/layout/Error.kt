package com.martin.jest.ui.base.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Error(onReload: () -> Unit) {
	Column(horizontalAlignment = Alignment.CenterHorizontally) {
		Icon(imageVector = Icons.Rounded.Warning, contentDescription = "")
		Text(text = "Something went wrong")
		Icon(
			imageVector = Icons.Rounded.Refresh,
			contentDescription = "",
			modifier = Modifier.clickable(onClick = onReload)
		)
	}
}