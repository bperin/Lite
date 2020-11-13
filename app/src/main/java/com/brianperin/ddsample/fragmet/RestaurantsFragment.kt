package com.brianperin.ddsample.fragmet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.brianperin.ddsample.R
import com.brianperin.ddsample.network.Result
import com.brianperin.ddsample.network.response.RestaurantNetwork
import com.brianperin.ddsample.util.Constants
import com.brianperin.ddsample.viewmodel.RestaurantsViewModel
import timber.log.Timber

/**
 * View model for grabbing our lists of restaurants given an input lat/lng
 * we can use our static coords for now or wait for listener to click in
 */
class RestaurantsFragment : BaseFragment() {

    companion object {
        fun newInstance() = RestaurantsFragment()
        const val BASE_LAT = 37.422740
        const val BASE_LNG = -122.139956
    }

    private val restaurantsViewModel = RestaurantsViewModel()

    private var lat = BASE_LAT
    private var lng = BASE_LNG

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_restaurants, container, false)

        restaurantsViewModel.getRestaurants(lat, lng, 10)
            .observe(viewLifecycleOwner, restaurantsObserver)

        return v
    }

    /**
     * invoke our view model to listen for life cycle changes in this case its network so it will fire every time
     * check the type to see if what state we're in loading, result, error
     * Observer what is omitted from the view model
     */
    private val restaurantsObserver = Observer<Result<List<RestaurantNetwork>>> {

        if (it.status == Result.Status.SUCCESS) {
            val restaurants = it.data!!

            restaurants.forEach { restaurant ->
                Timber.tag(Constants.TIMBER).d(restaurant.name)
            }
        }
    }

}