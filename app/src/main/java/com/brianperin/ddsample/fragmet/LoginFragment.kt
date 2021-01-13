package com.brianperin.ddsample.fragmet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brianperin.ddsample.R
import com.brianperin.ddsample.viewmodel.AuthViewModel


/**
 * View model for grabbing our lists of restaurants given an input lat/lng
 * we can use our static coords for now or wait for listener to click in
 */
class LoginFragment : BaseFragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val authViewModel = AuthViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_login, container, false)

        //TODO hard code for now would improve
//        storesViewModel.getStores(Constants.BASE_LAT, Constants.BASE_LNG, 100, 0).observe(viewLifecycleOwner, storesObserver)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    /**
     * invoke our view model to listen for life cycle changes in this case its network so it will fire every time
     * check the type to see if what state we're in loading, result, error
     * Observer what is omitted from the view model
     */
//    private val storesObserver = Observer<Result<StoreResponse>> {
//
//        when (it.status) {
//            Result.Status.ERROR -> {
//                IonAlert(context, IonAlert.ERROR_TYPE)
//                    .setTitleText(getString(R.string.oops))
//                    .setContentText(getString(R.string.something_went_wrong))
//                    .show()
//            }
//
//            Result.Status.LOADING -> {
//                showLoading()
//            }
//            Result.Status.SUCCESS -> {
//                storesAdapter.clear()
//                storesAdapter.setStores(it.data!!.stores)
//            }
//            else -> {
//                IonAlert(context, IonAlert.WARNING_TYPE)
//                    .setTitleText(getString(R.string.oops))
//                    .setContentText(getString(R.string.something_went_wrong))
//                    .show()
//            }
//        }
//        stopLoading()
//    }


    private fun showLoading() {

    }

    private fun stopLoading() {

    }

}