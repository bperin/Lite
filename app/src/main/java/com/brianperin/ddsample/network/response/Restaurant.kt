package com.brianperin.ddsample.network.response

import com.google.gson.annotations.SerializedName

data class Restaurant
    (
    private val id: String,

    @SerializedName("business_id")
    private val businessId: String,

    @SerializedName("is_time_surging")
    private val timeSurging: Boolean,

    @SerializedName("max_order_size")
    private val maxOderSize: Boolean,

    @SerializedName("max_composite_score")
    private val maxCompositeScore: Int,

    @SerializedName("merchant_promotions")
    private val merchantPromotions: List<String>,

    @SerializedName("average_rating")
    private val averageRating: Double,

    //TODO can we pull menus from here instead of extra network call
    private val menus: Any?,

    @SerializedName("composite_score")
    private val compositeScore: Double, //count be int?

    @SerializedName("status_type")
    private val statusType: String,

    @SerializedName("is_only_catering")
    private val onlyCatering: Boolean,

    private val status: String,

    @SerializedName("number_of_ratings")
    private val numberOfRatings: Int,

    @SerializedName("asap_time")
    private val asapTime: Any?,

    private val description: String,

    private val business: Business,

    private val tags: List<String>,

    @SerializedName("yelp_review_count")
    private val yelpReviewCount: Int,

    @SerializedName("extra_sos_delivery_fee")
    private val extraSosDeliveryFee: Double,

    @SerializedName("yelp_rating")
    private val yelpRating: Double,

    @SerializedName("cover_image_url")
    private val coverImage: String,

    @SerializedName("header_img_url")
    private val headerImageUrl: String,

    private val address: Address,

    @SerializedName("price_range")
    private val price_range: Double,

    private val slug: String,

    val name: String,

    @SerializedName("is_newly_added")
    private val newlyAdded: Boolean,

    private val url: String,

    @SerializedName("service_rate")
    private val serviceRate: Double,

    private val promotion: Any?,

    @SerializedName("featured_category_description")
    private val featuredCategoryDescription: Any?,
)