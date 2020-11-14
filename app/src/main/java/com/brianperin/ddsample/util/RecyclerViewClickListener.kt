package com.brianperin.ddsample.util

import android.view.View
import com.brianperin.ddsample.network.response.Restaurant

interface RecyclerViewClickListener {
    fun onClick(restaurant: Restaurant?, view: Int?, position: View?)
}