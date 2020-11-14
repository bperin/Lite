package com.brianperin.ddsample.fragmet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brianperin.ddsample.R
import com.brianperin.ddsample.network.response.Restaurant
import com.brianperin.ddsample.util.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DetailsFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(restaurant: Restaurant) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.RESTAURANT, restaurant)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val restaurant = arguments!!.getString(Constants.RESTAURANT)
    }

}