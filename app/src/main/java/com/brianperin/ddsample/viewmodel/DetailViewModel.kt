package com.brianperin.ddsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.brianperin.ddsample.network.Result
import com.brianperin.ddsample.network.repository.RestaurantsRepository
import com.brianperin.ddsample.network.response.Detail
import com.brianperin.ddsample.network.response.Restaurant
import kotlinx.coroutines.Dispatchers

/**
 * Our view model to handle fetching a single restaurant
 */
class DetailViewModel() : ViewModel() {

    private val restaurantsRepository = RestaurantsRepository()

    fun getRestaurant(id: String): LiveData<Result<Detail>> {
        return liveData(Dispatchers.IO) {

            emit(Result.loading())

            val data = restaurantsRepository.getRestaurant(id)

            emit(data)
        }
    }
}