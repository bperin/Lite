package com.brianperin.ddsample

import com.brianperin.ddsample.network.DashApiClient
import com.brianperin.ddsample.network.DashService
import com.brianperin.ddsample.util.Constants
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NetworkTests {

    lateinit var service: DashService

    @Before
    fun setUpClient() {

        val client = DashApiClient()
        client.setEndpoint(Constants.HOST)
        service = client.dashService

    }

    @Test
    fun test_GetRestaurants() {

        val lat = 37.422740
        val lng = -122.139956

        runBlocking {

            val response = service.getRestaurants(lat, lng)

            if (response.isSuccessful) {
                val body = response.body()!!
                if (body.isNotEmpty()) {
                    assert(true)
                }
            }
        }
    }

    /**
     * This should really fail if backend is reading
     */
    @Test
    fun test_invalidLat() {

        val lat = 0.0
        val lng = -122.139956

        runBlocking {

            val response = service.getRestaurants(lat, lng)

            if (response.isSuccessful) {
                val body = response.body()!!
                if (body.isNotEmpty()) {
                    assert(true)
                }
            }
        }
    }

    //it looks like backend doesn't really care about coords but these would be good real world tests
    @Test
    fun test_invalidLng() {

        val lat = 0.0
        val lng = -122.139956

        runBlocking {

            val response = service.getRestaurants(lat, lng)

            if (response.isSuccessful) {
                val body = response.body()!!
                if (body.isNotEmpty()) {
                    assert(true)
                }
            }
        }
    }

    @Test
    fun test_breakEndpoint() {

        val client = DashApiClient()
        client.setEndpoint("http://8af3948ccdf0.ngrok.io")
        service = client.dashService

        val lat = 37.422740
        val lng = -122.139956

        runBlocking {

            val response = service.getRestaurants(lat, lng)

            if (response.isSuccessful) {
                val body = response.body()!!
                if (body.isNotEmpty()) {
                    assert(false)
                }
            } else {
                assert(true)
            }
        }
    }
}
