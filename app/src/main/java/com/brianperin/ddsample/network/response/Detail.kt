package com.brianperin.ddsample.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Main detail model
 */
@Parcelize
data class Detail(

    val id: String = "",

    @SerializedName("phone_number")
    val phoneNumber: String = "",

    @SerializedName("yelp_review_count")
    val yelpReviewCount: Int = 0,

    @SerializedName("delivery_fee")
    val deliveryFee: Double = 0.0,

    @SerializedName("cover_img_url")
    val image: String = "",

    @SerializedName("average_rating")
    val averageRating: Double = 0.0,

    @SerializedName("delivery_radius")
    val deliveryRadius: Double = 0.0,

    @SerializedName("number_of_ratings")
    val numberOfRatings: Int = 0,

    val status: String = "",

    @SerializedName("printable_address")
    val printableAddress: String = "",

    @SerializedName("tags")
    val tags: List<String> = listOf(),

    ) : Parcelable