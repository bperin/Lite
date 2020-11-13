package com.brianperin.ddsample.network

import com.brianperin.ddsample.network.response.RestaurantNetwork
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DashService {

    @GET("api/v2/restaurant")
    suspend fun getRestaurants(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("limit") limit: Int
    ): Response<List<RestaurantNetwork>>

}