package com.martin.jest.di.modules

import android.app.Activity
import com.martin.jest.ui.base.activity.BaseActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class BaseActivityModule {

    @Provides
    fun provideBaseActivity(activity: Activity): BaseActivity {
        check(activity is BaseActivity) { "Every Activity is expected to extend BaseActivity" }
        return activity
    }
}