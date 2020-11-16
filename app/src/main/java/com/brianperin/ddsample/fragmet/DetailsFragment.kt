package com.brianperin.ddsample.fragmet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.brianperin.ddsample.R
import com.brianperin.ddsample.adapter.PopularItemsAdapter
import com.brianperin.ddsample.network.Result
import com.brianperin.ddsample.network.response.Detail
import com.brianperin.ddsample.network.response.Store
import com.brianperin.ddsample.util.Constants
import com.brianperin.ddsample.viewmodel.DetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import id.ionbit.ionalert.IonAlert
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(store: Store) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.STORE, store)
            }
        }
    }

    private val detailViewModel = DetailViewModel()
    lateinit var store: Store
    val popularItemsAdapter = PopularItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store = arguments!!.getParcelable(Constants.STORE)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val descriptions: List<String> = store.description.split("\"")
        popularItemsAdapter.setDescriptions(descriptions)

        detailViewModel.getRestaurant(store.id).observe(viewLifecycleOwner, detailObserver)

        recyclerPopularItems.layoutManager = LinearLayoutManager(context)
        recyclerPopularItems.adapter = popularItemsAdapter

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
                showDetails(it.data!!)
            }
            else -> {
                IonAlert(context, IonAlert.WARNING_TYPE)
                    .setTitleText(getString(R.string.oops))
                    .setContentText(getString(R.string.something_went_wrong))
                    .show()
            }
        }

    }

    /**
     * shows some basic stats from details
     */
    private fun showDetails(detail: Detail) {

        Picasso.get().load(detail.image).into(imageCoverView)
        detailHeader.text = store.name
        detailsRatingsCount.text = getString(R.string.ratings) + " " + detail.numberOfRatings.toString()
        detailsRatingsYelp.text = getString(R.string.yelp_ratings) + " " + detail.yelpReviewCount.toString()
        detailsAverageRating.text = getString(R.string.overall_rating) + " " + detail.averageRating.toString()

        popularItemsAdapter.setTags(detail.tags)

    }


}