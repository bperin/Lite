package com.brianperin.ddsample.util

import android.content.Context
import com.brianperin.ddsample.network.response.StoreResponse

open class StoreResponseCache(context: Context) : Cacheable<StoreResponse>(context, StoreResponse::class.java)