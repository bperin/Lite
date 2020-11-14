package com.brianperin.ddsample.util

import com.brianperin.ddsample.network.response.Restaurant

interface OnItemClickListener {
    fun onItemClick(item: Restaurant?)
}