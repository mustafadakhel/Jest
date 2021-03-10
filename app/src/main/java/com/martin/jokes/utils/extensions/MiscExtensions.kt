@file:Suppress("unused")

package com.martin.jokes.utils.extensions

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import kotlin.reflect.KClass

fun Throwable.log() {
    Log.e(toString(), "", this)
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

fun <V : ViewModel> ComponentActivity.vm(model: KClass<V>): Lazy<V> {

    return ViewModelLazy(model, { viewModelStore }, { defaultViewModelProviderFactory })
}
