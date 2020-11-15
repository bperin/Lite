package com.brianperin.ddsample

import android.app.Application
import com.brianperin.ddsample.network.DashApiClient
import com.brianperin.ddsample.util.Constants

/**
 * Our application entry we might want to set up some
 * singletons needing global application context
 */
class DdApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }

}