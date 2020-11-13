package com.brianperin.ddsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brianperin.ddsample.R
import com.brianperin.ddsample.network.response.RestaurantNetwork


/**
 * Our main adapter for showing a list of restaurants
 * Currently doesn't support adding / subtracting elements in an elegant way or endless scrolling
 * We dont want the adapter to take a constructor with the items in the case it changes
 */
class RestaurantsAdapter() : RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    private var restaurants = listOf<RestaurantNetwork>() //avoid concurrent manipulation

    fun setRestaurants(restaurants: List<RestaurantNetwork>) {
        this.restaurants = null
        this.restaurants = restaurants
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val restaurantView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_restaurant, parent, false)
        return ViewHolder(restaurantView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val name: TextView = itemView.findViewById<TextView>(R.id.tv_restaurat_name)
        val description: TextView = itemView.findViewById<TextView>(R.id.tv_restaurat_name)


    }
}