package com.brianperin.ddsample.fragmet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brianperin.ddsample.R
import com.mapbox.mapboxsdk.Mapbox

class MapFragment : BaseFragment() {


    companion object {
        fun newInstance() = MapFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)

        Mapbox.getInstance(context!!, getString(R.string.mapbox_access_token))

        return v
    }
}