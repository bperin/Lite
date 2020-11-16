package com.brianperin.ddsample.network.response

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    val stores: List<Store>
)