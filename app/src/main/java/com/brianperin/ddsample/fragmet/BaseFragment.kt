package com.brianperin.ddsample.fragmet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.brianperin.ddsample.util.Constants
import timber.log.Timber

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag(Constants.FRAGMENT_LIFECYCLE)
        Timber.d("onCreate " + this.javaClass.simpleName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = super.onCreateView(inflater, container, savedInstanceState)
        Timber.tag(Constants.FRAGMENT_LIFECYCLE)
        Timber.d("onCreateView " + this.javaClass.simpleName)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.tag(Constants.FRAGMENT_LIFECYCLE)
        Timber.d("onViewCreated " + this.javaClass.simpleName)
    }

    override fun onStart() {
        super.onStart()
        Timber.tag(Constants.FRAGMENT_LIFECYCLE)
        Timber.d("onStart " + this.javaClass.simpleName)
    }

    override fun onResume() {
        super.onResume()
        Timber.tag(Constants.FRAGMENT_LIFECYCLE)
        Timber.d("onResume " + this.javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        Timber.tag(Constants.FRAGMENT_LIFECYCLE)
        Timber.d("onPause " + this.javaClass.simpleName)
    }

    override fun onStop() {
        super.onStop()
        Timber.tag(Constants.FRAGMENT_LIFECYCLE)
        Timber.d("onStop " + this.javaClass.simpleName)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag(Constants.FRAGMENT_LIFECYCLE)
        Timber.d("onDestroy " + this.javaClass.simpleName)
    }

    //TODO error handing
//    fun getMainActivity(): MainActivity? {
//        activity?.let {
//            if (it is MainActivity) {
//                return it
//            }
//            return null
//        } ?: return null
//    }
//
//    fun getRegistrationActivity(): RegistrationActivity? {
//        activity?.let {
//            if (it is RegistrationActivity) {
//                return it
//            }
//            return null
//        } ?: return null
//    }

    //TODO move error out of base fragment since registration uses it too
    fun handleError(error: String?) {
        error?.let {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
    }
}