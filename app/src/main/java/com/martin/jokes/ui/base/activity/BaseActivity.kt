package com.martin.jokes.ui.base.activity

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.martin.jokes.ui.base.vm.BaseViewModel

abstract class BaseActivity : ComponentActivity() {

    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContent { Create() }
    }

    @Composable
    abstract fun Create()

}
