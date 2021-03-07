package com.martin.jokes.ui.activities.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.martin.jokes.BR
import com.martin.jokes.ui.navigators.base.BaseNavigator
import com.martin.jokes.utils.extensions.inflateWithDataBinding
import com.martin.jokes.utils.extensions.vm
import com.martin.jokes.vm.base.BaseViewModel
import kotlin.reflect.KClass


@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseActivity<T : ViewDataBinding, N : BaseNavigator, V : BaseViewModel<N>>
    (@LayoutRes private val layoutRes: Int) : AppCompatActivity() {

    private var viewDataBinding: T? = null
    val binding: T get() = viewDataBinding!!
    protected val viewModel: V by vm(getViewModelClass())

    abstract fun getNavigator(): N
    abstract fun getViewModelClass(): KClass<V>
    override fun setContentView(layoutRes: Int) {
        viewDataBinding = layoutInflater.inflateWithDataBinding(layoutRes)
        super.setContentView(viewDataBinding?.root)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = getNavigator()
        setContentView(layoutRes)
        viewDataBinding?.lifecycleOwner = this
        viewDataBinding?.setVariable(BR.navigator, getNavigator())
        viewDataBinding?.setVariable(BR.viewModel, viewModel)
        viewDataBinding?.executePendingBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewDataBinding = null
    }
}
