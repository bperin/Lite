package com.brianperin.ddsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.brianperin.ddsample.network.Result
import com.brianperin.ddsample.network.repository.RestaurantsRepository
import com.brianperin.ddsample.network.response.Restaurant
import kotlinx.coroutines.Dispatchers

/**
 * Our view model to handle fetching list of restaurants
 */
class RestaurantsViewModel() : ViewModel() {

    private val restaurantsRepository = RestaurantsRepository()

    fun getRestaurants(lat: Double, lng: Double): LiveData<Result<List<Restaurant>>> {
        return liveData(Dispatchers.IO) {

            emit(Result.loading())

            val data = restaurantsRepository.getRestaurants(lat, lng)

            emit(data)
        }
    }
}