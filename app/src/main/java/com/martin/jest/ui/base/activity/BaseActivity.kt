package com.martin.jest.ui.base.activity

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.martin.jest.ui.base.vm.BaseViewModel

abstract class BaseActivity : ComponentActivity() {

	abstract val viewModel: BaseViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		requestWindowFeature(Window.FEATURE_NO_TITLE)
		setContent {
			MaterialTheme {
				Surface(modifier = Modifier.fillMaxSize(), color = Color.Black.copy(alpha = 0.85f)) {
					Create()
				}
			}
		}
	}

	@Composable
	abstract fun Create()

}
