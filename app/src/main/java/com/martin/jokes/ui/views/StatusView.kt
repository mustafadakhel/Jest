package com.martin.jokes.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.martin.jokes.R
import com.martin.jokes.databinding.StatusViewLayoutBinding
import com.martin.jokes.utils.extensions.inflateWithDataBinding
import com.martin.jokes.models.base.Result
import com.martin.jokes.ui.navigators.base.BaseNavigator

@Suppress("MemberVisibilityCanBePrivate")
class StatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    var navigator: BaseNavigator? = null
    var status: Result.Status? = null
        set(value) {
            field = value
            isVisible = value == Result.Status.ERROR || value == Result.Status.LOADING
            binding.statusRetryButton.isVisible = value == Result.Status.ERROR
            binding.statusErrorText.isVisible = value == Result.Status.ERROR
            binding.statusErrorTextSecondary.isVisible = value == Result.Status.ERROR
            binding.statusProgressBar.isVisible = value == Result.Status.LOADING
        }

    val binding: StatusViewLayoutBinding by lazy {
        LayoutInflater.from(context).inflateWithDataBinding(R.layout.status_view_layout, this, true)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        status = status
        if (context is BaseNavigator)
            navigator = context as? BaseNavigator
        binding.statusRetryButton.setOnClickListener {
            navigator?.onReload()
        }
    }

    override fun onDetachedFromWindow() {
        navigator = null
        status = status
        super.onDetachedFromWindow()
    }
}