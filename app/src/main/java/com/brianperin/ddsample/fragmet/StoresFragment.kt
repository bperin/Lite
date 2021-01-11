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
import com.brianperin.ddsample.adapter.StoresAdapter
import com.brianperin.ddsample.network.Result
import com.brianperin.ddsample.network.response.Store
import com.brianperin.ddsample.network.response.StoreResponse
import com.brianperin.ddsample.util.Constants
import com.brianperin.ddsample.util.Promos
import com.brianperin.ddsample.util.StoreClickListenener
import com.brianperin.ddsample.viewmodel.StoresViewModel
import id.ionbit.ionalert.IonAlert
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*
import kotlinx.android.synthetic.main.fragment_restaurants.*
import kotlinx.android.synthetic.main.fragment_restaurants.recyclerRestaurants
import timber.log.Timber


/**
 * View model for grabbing our lists of restaurants given an input lat/lng
 * we can use our static coords for now or wait for listener to click in
 */
class StoresFragment : BaseFragment() {

    companion object {
        fun newInstance() = StoresFragment()
    }

    private val storesAdapter = StoresAdapter()
    private val storesViewModel = StoresViewModel()

    lateinit var rotate: Animation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_restaurants, container, false)

        //TODO hard code for now would improve
        storesViewModel.getStores(Constants.BASE_LAT, Constants.BASE_LNG, 100, 0).observe(viewLifecycleOwner, storesObserver)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading()

        recyclerRestaurants.layoutManager = LinearLayoutManager(context)
        recyclerRestaurants.adapter = storesAdapter
        storesAdapter.setListener(storeClickListener)

        buttonDismissAllPromos.setOnClickListener {
            Promos.dismiss("1")
            buttonDismissAllPromos.visibility = View.GONE
        }

        if (Promos.isDismissed("1")) {
            buttonDismissAllPromos.visibility = View.GONE
        }

    }

    /**
     * invoke our view model to listen for life cycle changes in this case its network so it will fire every time
     * check the type to see if what state we're in loading, result, error
     * Observer what is omitted from the view model
     */
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
                val stores = mutableListOf<Store>()
                it.data!!.stores.forEach {

                    if (!Promos.isDismissed(it.id)) {
                        stores.add(it)
                    }

                }
                storesAdapter.setStores(stores)
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

    private val storeClickListener = object : StoreClickListenener {
        override fun onClick(store: Store?, view: Int?, position: View?) {
            Timber.tag(Constants.TIMBER).d(store?.name)
            showDetailsFragment(store!!)
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

    private fun showDetailsFragment(store: Store) {
        val detailsFragment = DetailsFragment.newInstance(store)
        detailsFragment.show(childFragmentManager, DetailsFragment.javaClass.name)
    }
}