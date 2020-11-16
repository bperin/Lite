package com.brianperin.ddsample.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Store(

    val id: String,
    @SerializedName("num_ratings")
    val numRatings: String,
    @SerializedName("delivery_fee_monetary_fields")
    val deliveryFee: DeliveryFee,
    @SerializedName("extra_sos_delivery_fee_monetary_fields")
    val surgeFee: SurgeFee,
    @SerializedName("average_rating")
    val averageRating: Float,
    val location: Location,
    val status: Status,
    val description: String,
    @SerializedName("price_range")
    val priceRange: Int,
    @SerializedName("cover_img_url")
    val coverImage: String?,
    @SerializedName("header_img_url")
    val headerImage: String?,
    val name: String,
    @SerializedName("distance_from_consumer")
    val distance: Double

) : Parcelable {
    @Parcelize
    data class Location(
        val lat: Double,
        val lng: Double
    ) : Parcelable

    @Parcelize
    data class DeliveryFee(
        @SerializedName("unit_amount")
        val unitAmount: Double,

        @SerializedName("display_string")
        val displayString: String
    ) : Parcelable

    @Parcelize
    data class SurgeFee(
        @SerializedName("unit_amount")
        val unitAmount: Double,

        @SerializedName("display_string")
        val displayString: String
    ) : Parcelable

    @Parcelize
    data class Status(
        val id: String,
        @SerializedName("asap_minutes_range")
        val range: List<String>
    ) : Parcelable
}