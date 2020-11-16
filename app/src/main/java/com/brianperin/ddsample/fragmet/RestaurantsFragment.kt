package com.brianperin.ddsample.fragmet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.brianperin.ddsample.R
import com.brianperin.ddsample.adapter.RestaurantsAdapter
import com.brianperin.ddsample.adapter.StoresAdapter
import com.brianperin.ddsample.network.Result
import com.brianperin.ddsample.network.response.Restaurant
import com.brianperin.ddsample.network.response.Store
import com.brianperin.ddsample.network.response.StoreResponse
import com.brianperin.ddsample.util.Constants
import com.brianperin.ddsample.util.RecyclerViewClickListener
import com.brianperin.ddsample.util.StoreClickListenener
import com.brianperin.ddsample.viewmodel.RestaurantsViewModel
import com.brianperin.ddsample.viewmodel.StoresViewModel
import id.ionbit.ionalert.IonAlert
import kotlinx.android.synthetic.main.fragment_restaurants.*
import timber.log.Timber


/**
 * View model for grabbing our lists of restaurants given an input lat/lng
 * we can use our static coords for now or wait for listener to click in
 */
class RestaurantsFragment : BaseFragment() {

    companion object {
        fun newInstance() = RestaurantsFragment()
    }

    //    private val restaurantsViewModel = RestaurantsViewModel()
//    private val restaurantsAdapter = RestaurantsAdapter()
    private val storesAdapter = StoresAdapter()
    private val storesViewModel = StoresViewModel()

    lateinit var rotate: Animation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_restaurants, container, false)

//        restaurantsViewModel.getRestaurants(Constants.BASE_LAT, Constants.BASE_LNG).observe(viewLifecycleOwner, restaurantsObserver)
        storesViewModel.getStores(Constants.BASE_LAT, Constants.BASE_LNG, 50, 0).observe(viewLifecycleOwner, storesObserver)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerRestaurants.layoutManager = LinearLayoutManager(context)
//        recyclerRestaurants.adapter = restaurantsAdapter
        recyclerRestaurants.adapter = storesAdapter
        storesAdapter.setListener(storeClickListener)
//        restaurantsAdapter.setListener(clickListener)
    }

    /**
     * invoke our view model to listen for life cycle changes in this case its network so it will fire every time
     * check the type to see if what state we're in loading, result, error
     * Observer what is omitted from the view model
     */
    private val restaurantsObserver = Observer<Result<List<Restaurant>>> {

        when (it.status) {
            Result.Status.ERROR -> {
                IonAlert(context, IonAlert.ERROR_TYPE)
                    .setTitleText(getString(R.string.oops))
                    .setContentText(getString(R.string.something_went_wrong))
                    .show()
            }

            Result.Status.LOADING -> {
                showLoading()
            }
            Result.Status.SUCCESS -> {
//                restaurantsAdapter.setRestaurants(it.data!!)
            }
            else -> {
                IonAlert(context, IonAlert.WARNING_TYPE)
                    .setTitleText(getString(R.string.oops))
                    .setContentText(getString(R.string.something_went_wrong))
                    .show()
            }
        }
        stopLoading()
    }

    private val storesObserver = Observer<Result<StoreResponse>> {

        when (it.status) {
            Result.Status.ERROR -> {
                IonAlert(context, IonAlert.ERROR_TYPE)
                    .setTitleText(getString(R.string.oops))
                    .setContentText(getString(R.string.something_went_wrong))
                    .show()
            }

            Result.Status.LOADING -> {
                showLoading()
            }
            Result.Status.SUCCESS -> {
                storesAdapter.setStores(it.data!!.stores)
            }
            else -> {
                IonAlert(context, IonAlert.WARNING_TYPE)
                    .setTitleText(getString(R.string.oops))
                    .setContentText(getString(R.string.something_went_wrong))
                    .show()
            }
        }
        stopLoading()
    }

    private val clickListener = object : RecyclerViewClickListener {
        override fun onClick(restaurant: Restaurant?, view: Int?, position: View?) {
            Timber.tag(Constants.TIMBER).d(restaurant?.name)
            showDetailsFragment(restaurant!!)
        }
    }
    private val storeClickListener = object : StoreClickListenener {
        override fun onClick(store: Store?, view: Int?, position: View?) {
            Timber.tag(Constants.TIMBER).d(store?.name)
//            showDetailsFragment(restaurant!!)
        }
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
        rotate = AnimationUtils.loadAnimation(context?.applicationContext, R.anim.anti_clock)
        loading.startAnimation(rotate)
    }

    private fun stopLoading() {
        rotate.cancel()
        loading.visibility = View.GONE
    }

    private fun showDetailsFragment(restaurant: Restaurant) {
        val detailsFragment = DetailsFragment.newInstance(restaurant)
        detailsFragment.show(childFragmentManager, DetailsFragment.javaClass.name)
    }
}