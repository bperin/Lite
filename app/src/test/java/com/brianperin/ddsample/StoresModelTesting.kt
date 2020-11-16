package com.brianperin.ddsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.brianperin.ddsample.network.repository.RestaurantsRepository
import com.brianperin.ddsample.network.response.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.coroutines.CoroutineContext


@RunWith(JUnit4::class)
class StoresModelTesting {

    private var instantTaskExecutorRule = InstantTaskExecutorRule()
    private var repo = RestaurantsRepository()

    private var store: MutableLiveData<Store> = MutableLiveData()

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun getStore() = store

}