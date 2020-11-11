package com.brianperin.ddsample.activity

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brianperin.ddsample.R
import com.brianperin.ddsample.fragmet.RestaurantsFragment
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions

/**
 * Entry point into activities and will control fragments
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showRestaurantsFragment()
    }

    /**
     * Init our main fragment with needed permission todo handle non permission access
     */
    private fun showRestaurantsFragment() = runWithPermissions(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) {

        val restaurantsFragment = RestaurantsFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_holder, restaurantsFragment)
            .commit()
    }
}