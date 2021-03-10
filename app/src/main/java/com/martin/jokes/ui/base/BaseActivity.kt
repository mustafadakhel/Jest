package com.martin.jokes.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.martin.jokes.vm.base.BaseViewModel


@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseActivity : ComponentActivity() {

    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Create() }
    }

    @Composable
    abstract fun Create()

}
