package com.brianperin.ddsample.activity

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brianperin.ddsample.R
import com.brianperin.ddsample.fragmet.MapFragment
import com.brianperin.ddsample.fragmet.StoresFragment
import com.fxn.OnBubbleClickListener
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Entry point into activities and will control fragments
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        showRestaurantsFragment()


        bubbleTabBar.addBubbleListener(object : OnBubbleClickListener {
            override fun onBubbleClick(id: Int) {
                when (id) {
                    R.id.home -> showRestaurantsFragment()
                    R.id.map -> showMapFragment()
                }
            }
        })
    }

    /**
     * Init our main fragment with needed permission todo handle non permission access
     */
    private fun showRestaurantsFragment() = runWithPermissions(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) {

        val restaurantsFragment = StoresFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_holder, restaurantsFragment)
            .commit()
    }

    /**
     * Shows the map view
     */
    fun showMapFragment() = runWithPermissions(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) {

        val mapsFragment = MapFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_holder, mapsFragment)
            .commit()
    }
}