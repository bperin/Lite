package com.brianperin.ddsample

import android.app.Application
import com.brianperin.ddsample.network.DashApiClient
import com.brianperin.ddsample.util.Constants


class DdApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DashApiClient.setEndpoint(Constants.HOST)
    }

}