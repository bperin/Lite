package com.brianperin.ddsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brianperin.ddsample.R
import kotlinx.android.synthetic.main.fragment_restaurants.view.*


class RestaurantsAdapter() : RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val contactView =  LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_restaurant, parent, false)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val nameTextView = itemView.findViewById<TextView>(R.id.contact_name)
        val messageButton = itemView.findViewById<TextView>(R.id.message_button)
    }
}