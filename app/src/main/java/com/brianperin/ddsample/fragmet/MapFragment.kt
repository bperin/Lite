package com.brianperin.ddsample.fragmet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.brianperin.ddsample.R
import com.brianperin.ddsample.network.Result
import com.brianperin.ddsample.network.response.Store
import com.brianperin.ddsample.network.response.StoreResponse
import com.brianperin.ddsample.util.Constants
import com.brianperin.ddsample.viewmodel.StoresViewModel
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.Style
import id.ionbit.ionalert.IonAlert
import kotlinx.android.synthetic.main.fragment_map.*

/**
 * Map fragment copying a lot of logic from restaurant fragment
 * can probably improve by sharing view model between the two or using common activity to fetch data
 * just shows pins for now
 */
class MapFragment : BaseFragment() {

    companion object {
        fun newInstance() = MapFragment()
    }

    lateinit var mv: MapView
    private val storesViewModel = StoresViewModel()
    lateinit var mapBox: Mapbox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapBox = Mapbox.getInstance(context!!, getString(R.string.mapbox_access_token))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)

        //TODO again remove hardcode
        storesViewModel.getStores(Constants.BASE_LAT, Constants.BASE_LNG, 100, 0).observe(viewLifecycleOwner, storesObserver)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mv = mapView
        mv.onCreate(savedInstanceState)
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
                //TODO
            }
            Result.Status.SUCCESS -> {
                showMarkers(it.data!!.stores)
            }
            else -> {
                IonAlert(context, IonAlert.WARNING_TYPE)
                    .setTitleText(getString(R.string.oops))
                    .setContentText(getString(R.string.something_went_wrong))
                    .show()
            }
        }
        //TODO
    }

    /**
     * Shows all our markers, MB makes you create markers before you
     * render the map for a weird reason so we have go create the map after we get our
     * stores response back
     */
    private fun showMarkers(stores: List<Store>) {
        if (!stores.isNullOrEmpty()) {
            val ops = mutableListOf<MarkerOptions>()
            stores.forEach { store ->
                val latLng = LatLng(store.location.lat, store.location.lng)
                val options: MarkerOptions = MarkerOptions().position(latLng).title(store.name)
                ops.add(options)
            }
            mv.getMapAsync { mapBox ->
                mapBox.addMarkers(ops)
                mapBox.setStyle(Style.MAPBOX_STREETS) {

                }
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