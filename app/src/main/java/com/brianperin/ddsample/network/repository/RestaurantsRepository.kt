package com.brianperin.ddsample.network.repository

import com.brianperin.ddsample.network.BaseDataSource
import com.brianperin.ddsample.network.DashApiClient
import com.brianperin.ddsample.network.DashService

/**
 * Repo layer for fetching restaurants now we're using HTTP can improve with a middle layer DB
 */
class RestaurantsRepository() : BaseDataSource() {

    var client: DashService = DashApiClient.dashService

    suspend fun getRestaurants(lat: Double, lng: Double, limit: Int) = getResult { client.getRestaurants(lat,lng,limit) }

}