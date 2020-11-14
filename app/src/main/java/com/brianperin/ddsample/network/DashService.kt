package com.brianperin.ddsample.network

import com.brianperin.ddsample.network.response.Detail
import com.brianperin.ddsample.network.response.Restaurant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DashService {

    @GET("v2/restaurant")
    suspend fun getRestaurants(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("limit") limit: Int
    ): Response<List<Restaurant>>

    @GET("v2/restaurant/{id}/")
    suspend fun getRestaurant(@Path("id") id: String): Response<Detail>

}