package com.martin.jokes.ui.base

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.martin.jokes.utils.extensions.vm
import com.martin.jokes.vm.base.BaseViewModel

abstract class BaseActivity : ComponentActivity() {

    open val viewModel: BaseViewModel by vm(BaseViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContent { Create() }
    }

    @Composable
    abstract fun Create()

}
