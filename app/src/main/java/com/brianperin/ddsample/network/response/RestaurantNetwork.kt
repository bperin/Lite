package com.brianperin.ddsample.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Main restaurant model
 */
@Parcelize
data class RestaurantNetwork(

   internal val id: String,

   internal val mame: String,

   internal val description: String,

   @SerializedName("delivery_fee")
   internal val deliveryFee: Double,

   @SerializedName("cover_img_url")
   internal val image: String,

   internal val status: String

) : Parcelable