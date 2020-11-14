package com.brianperin.ddsample.fragmet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.brianperin.ddsample.R
import com.brianperin.ddsample.network.Result
import com.brianperin.ddsample.network.response.Detail
import com.brianperin.ddsample.network.response.Restaurant
import com.brianperin.ddsample.util.Constants
import com.brianperin.ddsample.viewmodel.DetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.ionbit.ionalert.IonAlert


class DetailsFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(restaurant: Restaurant) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.RESTAURANT, restaurant)
            }
        }
    }

    private val detailViewModel = DetailViewModel()
    lateinit var restaurant: Restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restaurant = arguments!!.getParcelable(Constants.RESTAURANT)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        detailViewModel.getRestaurant(restaurant.id).observe(viewLifecycleOwner, detailObserver)

    }

    private val detailObserver = Observer<Result<Detail>> {

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

            }
            else -> {
                IonAlert(context, IonAlert.WARNING_TYPE)
                    .setTitleText(getString(R.string.oops))
                    .setContentText(getString(R.string.something_went_wrong))
                    .show()
            }
        }

    }


}