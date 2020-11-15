package com.brianperin.ddsample

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree


/**
 * Our application entry we might want to set up some
 * singletons needing global application context
 */
class DdApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }

}