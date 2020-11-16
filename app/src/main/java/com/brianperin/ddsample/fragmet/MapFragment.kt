package com.brianperin.ddsample.fragmet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.brianperin.ddsample.R
import com.brianperin.ddsample.network.Result
import com.brianperin.ddsample.network.response.Restaurant
import com.brianperin.ddsample.util.Constants
import com.brianperin.ddsample.viewmodel.RestaurantsViewModel
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.Style
import id.ionbit.ionalert.IonAlert
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : BaseFragment() {

    companion object {
        fun newInstance() = MapFragment()
    }

    lateinit var mv: MapView
    private val restaurantsViewModel = RestaurantsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(context!!, getString(R.string.mapbox_access_token))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)

        restaurantsViewModel.getRestaurants(Constants.BASE_LAT, Constants.BASE_LNG).observe(viewLifecycleOwner, restaurantsObserver)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mv = mapView
        mv?.onCreate(savedInstanceState)
        mv?.getMapAsync { mapboxMap ->

            mapboxMap.setStyle(Style.MAPBOX_STREETS) {

            }

        }
    }

    private val restaurantsObserver = Observer<Result<List<Restaurant>>> {

        when (it.status) {
            Result.Status.ERROR -> {
                IonAlert(context, IonAlert.ERROR_TYPE)
                    .setTitleText(getString(R.string.oops))
                    .setContentText(getString(R.string.something_went_wrong))
                    .show()
            }

            Result.Status.LOADING -> {

            }
            Result.Status.SUCCESS -> {
                showMarkers(it.data!!)
            }
            else -> {
                IonAlert(context, IonAlert.WARNING_TYPE)
                    .setTitleText(getString(R.string.oops))
                    .setContentText(getString(R.string.something_went_wrong))
                    .show()
            }
        }

    }

    fun showMarkers(restaurants: List<Restaurant>) {
        if (!restaurants.isNullOrEmpty()) {
            restaurants.forEach { restaurant ->
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mv.onStart()
    }

    override fun onStop() {
        super.onStop()
        mv.onStop()
    }

    override fun onResume() {
        super.onResume()
        mv.onResume()
    }

    override fun onPause() {
        super.onPause()
        mv.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mv.onDestroy()
    }
}