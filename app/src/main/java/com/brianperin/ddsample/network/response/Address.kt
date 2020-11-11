package com.brianperin.ddsample.network.response

import com.google.gson.annotations.SerializedName

data class Address(

    private val city: String,

    private val state: String,

    private val street: String,

    @SerializedName("printable_address")
    private val printableAddress: String,

    private val lat: Double,

    private val lng: Double
)