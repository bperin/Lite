package com.brianperin.ddsample

import com.brianperin.ddsample.network.response.Restaurant
import org.junit.Before
import org.junit.Test

class RestaurantModelTests {

    val restaurants = mutableListOf<Restaurant>()

    @Before
    fun makeObjects() {

        val restaurant1 = Restaurant("1", "some place", "come dine in ", 1.0, "http://", "open")
        val restaurant2 = Restaurant("2", "", "come dine in ", 1.0, "http://", "open")
        val restaurant3 = Restaurant("3", "some  other place", "come dine in ", 1.0, "http://")
        val restaurant4 = Restaurant("1", "some place2", "come dine in ", 1.0, "http://", "open")

        restaurants.add(restaurant1)
        restaurants.add(restaurant2)
        restaurants.add(restaurant3)
        restaurants.add(restaurant4)
    }

    @Test
    fun test_AllFieldsPresent() {

        val restaurant = restaurants[0]

        val anyElementNull = listOf(restaurant.id, restaurant.name, restaurant.description, restaurant.description, restaurant.image, restaurant.status).any { it.isNullOrEmpty() }

        assert(!anyElementNull)
    }

    @Test
    fun test_descriptionEmpty() {

        val restaurant = restaurants[1]

        val anyElementNull = listOf(restaurant.id, restaurant.name, restaurant.description, restaurant.description, restaurant.image, restaurant.status).any { it.isNullOrEmpty() }

        assert(anyElementNull)
    }

    @Test
    fun test_statusMissing() {

        val restaurant = restaurants[2]

        val anyElementNull = listOf(restaurant.id, restaurant.name, restaurant.description, restaurant.description, restaurant.image, restaurant.status).any { it.isNullOrEmpty() }

        assert(anyElementNull)
    }
}