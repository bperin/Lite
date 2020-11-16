package com.brianperin.ddsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brianperin.ddsample.R
import com.brianperin.ddsample.network.response.Restaurant
import com.brianperin.ddsample.network.response.Store
import com.brianperin.ddsample.util.RecyclerViewClickListener
import com.brianperin.ddsample.util.StoreClickListenener
import com.squareup.picasso.Picasso
import me.zhanghai.android.materialratingbar.MaterialRatingBar
import java.lang.StringBuilder
import kotlin.math.round


/**
 * Our main adapter for showing a list of restaurants
 * Currently doesn't support adding / subtracting elements in an elegant way or endless scrolling
 * We dont want the adapter to take a constructor with the items in the case it changes
 * Add a click constructor to handle recycler item clicks
 */
class StoresAdapter : RecyclerView.Adapter<StoresAdapter.ViewHolder>() {

    private var stores = listOf<Store>() //avoid concurrent manipulation
    private lateinit var itemClickListener: StoreClickListenener

    fun setStores(stores: List<Store>) {
        this.stores = emptyList()
        this.stores = stores
        notifyDataSetChanged()
    }

    /**
     * Custom listener
     */
    fun setListener(storeClickListenener: StoreClickListenener) {
        this.itemClickListener = storeClickListenener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val storeView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_store, parent, false)
        return ViewHolder(storeView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val store = stores[position]

        holder.name.text = store.name
        holder.description.text = store.description
        holder.ratingBar.rating = store.averageRating

        holder.price.text = ""

        val builder = StringBuilder()

        for (x in 0 until store.priceRange) {
            builder.append("$")
        }

        holder.price.text = builder.toString()

        val min1 = store.status.range.getOrNull(0)
        val min2 = store.status.range.getOrNull(1)
        val distance = round(store.distance)

        val details = "$distance miles, $min1-$min2 minute delivery time"

        holder.details.text = details

        val image: String = if (!store.headerImage.isNullOrEmpty()) store.headerImage else store.coverImage!!

        Picasso.get()
            .load(image)
            .placeholder(R.drawable.logo)
            .into(holder.cover)

        val listener = itemClickListener

        holder.itemView.setOnClickListener {
            listener.onClick(store, position, it)
        }

    }

    override fun getItemCount(): Int {
        return stores.size
    }

    /**
     * View holder for our items
     */
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        var cover: ImageView = itemView.findViewById<ImageView>(R.id.iv_store_header)
        var name: TextView = itemView.findViewById<TextView>(R.id.tv_store_name)
        var description: TextView = itemView.findViewById<TextView>(R.id.tv_store_description)
        var details: TextView = itemView.findViewById<TextView>(R.id.tv_store_details)
        var price: TextView = itemView.findViewById<TextView>(R.id.tv_store_price)
        var ratingBar: MaterialRatingBar = itemView.findViewById<MaterialRatingBar>(R.id.rating_bar)


    }
}