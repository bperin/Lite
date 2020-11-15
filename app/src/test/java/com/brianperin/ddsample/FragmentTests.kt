package com.brianperin.ddsample

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brianperin.ddsample.activity.MainActivity
import com.brianperin.ddsample.fragmet.RestaurantsFragment
import org.junit.AfterClass
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentTests {



    @Before
    fun setup(){

    }
    @Test
    fun testEvent() {
        val scenario = launchActivity<MainActivity>()
    }

    @Test
    private fun test_CreateRestaurantsFragment() {
        val fragment = RestaurantsFragment.newInstance()

    }

    @AfterClass
    fun afterClass(){

    }
}