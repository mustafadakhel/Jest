package com.martin.jokes.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.martin.jokes.utils.extensions.vm
import com.martin.jokes.vm.base.BaseViewModel
import kotlin.reflect.KClass


@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseActivity<V : BaseViewModel> : ComponentActivity() {

    protected val viewModel: V by vm(getViewModelClass())

    abstract fun getViewModelClass(): KClass<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Create() }
    }

    @Composable
    abstract fun Create()

}
