package com.brianperin.ddsample.network.repository

import com.brianperin.ddsample.network.BaseDataSource
import com.brianperin.ddsample.network.DashApiClient
import com.brianperin.ddsample.network.DashService
import com.brianperin.ddsample.util.Constants

/**
 * Repo layer for fetching restaurants now we're using HTTP can improve with a middle layer DB
 */
class RestaurantsRepository() : BaseDataSource() {

    var service: DashService

    init {
        val client = DashApiClient()
        client.setEndpoint(Constants.HOST)
        service = client.dashService

    }



    suspend fun getRestaurants(lat: Double, lng: Double) = getResult { service.getRestaurants(lat, lng) }

    suspend fun getRestaurant(id: String) = getResult { service.getRestaurant(id) }

}