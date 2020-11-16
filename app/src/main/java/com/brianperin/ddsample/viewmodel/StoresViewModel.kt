package com.brianperin.ddsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.brianperin.ddsample.network.Result
import com.brianperin.ddsample.network.repository.RestaurantsRepository
import com.brianperin.ddsample.network.response.Restaurant
import com.brianperin.ddsample.network.response.StoreResponse
import kotlinx.coroutines.Dispatchers

/**
 * Our view model to handle fetching list of stores
 */
class StoresViewModel() : ViewModel() {

    private val restaurantsRepository = RestaurantsRepository()

    fun getStores(lat: Double, lng: Double, limit: Int, offset: Int): LiveData<Result<StoreResponse>> {
        return liveData(Dispatchers.IO) {

            emit(Result.loading())

            val data = restaurantsRepository.getStores(lat, lng, offset, limit)

            emit(data)
        }
    }
}