package com.brianperin.ddsample.util

import android.view.View
import com.brianperin.ddsample.network.response.Store

interface StoreClickListenener {
    fun onClick(store: Store?, view: Int?, position: View?)
}