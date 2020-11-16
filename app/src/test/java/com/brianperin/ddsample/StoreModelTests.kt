package com.brianperin.ddsample

import com.brianperin.ddsample.network.response.Store
import org.junit.Before
import org.junit.Test

class StoreModelTests {

    private val stores = mutableListOf<Store>()

    @Before
    fun makeObjects() {

        val store1 = Store(
            "1", "100", Store.DeliveryFee(0.0, "1"), Store.SurgeFee(0.0, "1"),
            2.3F, Store.Location(-91.0, 91.0), Store.Status("1", listOf("20", "30")), "some cool spot", 2, "http://",
            "http://", "brians house", 1.0
        )

        val store2 = Store(
            "2", "100", Store.DeliveryFee(0.0, "1"), Store.SurgeFee(0.0, "1"),
            2.3F, Store.Location(100.0, 190.0), Store.Status("1", listOf("20", "30")), "some cool spot", 2, "http://",
            "http://", "brians house", 1.0
        )


        stores.add(store1)
        stores.add(store2)

    }

    @Test
    fun test_AllFieldsPresent() {

        val store = stores[0]

        val anyElementNull = listOf(
            store.id, store.numRatings, store.deliveryFee.toString(), store.surgeFee.toString(), store.averageRating.toString(), store.location.toString(),
            store.status.toString(), store.description, store.priceRange.toString(), store.coverImage, store.headerImage, store.name, store.distance.toString()
        ).any { it.isNullOrEmpty() }

        assert(!anyElementNull)
    }

    @Test
    fun test_outOfBoundsLat() {
        val store = stores[1]
        val lat = store.location.lat
        assert(!(lat >= -90.0 && lat <= 90.0))
    }
    @Test
    fun test_outOfBoundsLng() {
        val store = stores[1]
        val lng = store.location.lng
        assert(!(lng >= -180.0 && lng <= 180.0))
    }

}