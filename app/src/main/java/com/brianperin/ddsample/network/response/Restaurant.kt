package com.brianperin.ddsample.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Main restaurant model
 */
@Parcelize
data class Restaurant(

    val id: String = "",

    val name: String = "",

    val description: String = "",

    @SerializedName("delivery_fee")
    val deliveryFee: Double = 0.0,

    @SerializedName("cover_img_url")
    val image: String = "",

    val status: String = ""

) : Parcelable