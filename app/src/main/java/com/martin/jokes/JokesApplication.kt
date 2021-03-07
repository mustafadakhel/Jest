package com.martin.jokes

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JokesApplication : Application() {
    lateinit var context: Context

    override fun attachBaseContext(base: Context) {
        context = base
        super.attachBaseContext(context)
    }
}