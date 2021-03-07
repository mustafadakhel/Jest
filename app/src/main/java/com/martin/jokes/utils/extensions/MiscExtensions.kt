package com.martin.jokes.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import kotlin.reflect.KClass

fun Throwable.log() {
    Log.e(toString(), "", this)
}

fun Context.getCompatColorStateList(@ColorRes colorStateRes: Int): ColorStateList? {
    return ContextCompat.getColorStateList(this, colorStateRes)

}

fun Context.getCompatColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun Context.getCompatDrawable(@DrawableRes drawableRes: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableRes)
}

fun Activity.showToast(@StringRes stringRes: Int) {
    quickToast(this)?.apply {
        setText(stringRes)
        show()
    }
}

fun Activity.showToast(string: String) {
    quickToast(this)?.apply {
        setText(string)
        show()
    }
}

fun Fragment.showToast(text: String) {
    quickToast(requireContext())?.apply {
        setText(text)
        show()
    }
}

fun Fragment.showToast(@StringRes stringRes: Int) {
    quickToast(requireContext())?.apply {
        setText(stringRes)
        show()
    }
}

private fun quickToast(
    context: Context,
    text: String? = null,
    duration: Int = Toast.LENGTH_LONG
): Toast? {
    return Toast.makeText(context, text, duration)
}

fun <T : ViewDataBinding> LayoutInflater.inflateWithDataBinding(
    @LayoutRes layout: Int,
    container: ViewGroup? = null,
    attachToParent: Boolean = false
): T {
    return DataBindingUtil.inflate(
        this,
        layout,
        container,
        if (container != null) attachToParent else false
    )
}

fun <V : ViewModel> ComponentActivity.vm(model: KClass<V>): Lazy<V> {

    return ViewModelLazy(model, { viewModelStore }, { defaultViewModelProviderFactory })
}
