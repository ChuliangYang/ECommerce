package com.me.cl.popularmovie.mvvm

import android.app.Activity
import android.app.Application
import com.me.cl.popularmovie.mvvm.di.DaggerAutoInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by CL on 10/8/18.
 */
class MovieApplication:Application(),HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAutoInjector.inject(this)
    }
}