package com.brianperin.ddsample.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.brianperin.ddsample.network.Result
import com.brianperin.ddsample.network.repository.RestaurantsRepository
import com.brianperin.ddsample.network.response.StoreResponse
import com.brianperin.ddsample.util.StoreResponseCache
import kotlinx.coroutines.Dispatchers

/**
 * Our view model to handle fetching list of stores
 */
class StoresViewModel(context: Context) : ViewModel() {

    private val restaurantsRepository = RestaurantsRepository()
    private val cache = StoreResponseCache(context)

    fun getStores(lat: Double, lng: Double, limit: Int, offset: Int): LiveData<Result<StoreResponse>> {

        return liveData(Dispatchers.IO) {

            emit(Result.loading())

            cache.get()?.let {
                val cachedResult = Result(Result.Status.SUCCESS, it, null)
                emit(cachedResult)
            }
            val stores = restaurantsRepository.getStores(lat, lng, offset, limit)

            cache.set(stores.data)

            emit(stores)
        }
    }
}