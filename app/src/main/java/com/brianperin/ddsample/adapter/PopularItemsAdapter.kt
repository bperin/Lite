package com.brianperin.ddsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brianperin.ddsample.R


/**
 * Our main adapter for showing a list of restaurants
 * Currently doesn't support adding / subtracting elements in an elegant way or endless scrolling
 * We dont want the adapter to take a constructor with the items in the case it changes
 * Add a click constructor to handle recycler item clicks
 */
class PopularItemsAdapter : RecyclerView.Adapter<PopularItemsAdapter.ViewHolder>() {

    private var items = listOf<String>()

    /**
     * Descriptions will come first since we get them from list of restaurants
     */
    fun setDescriptions(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    /**
     * Set tags but dont want to run into recursion so copy or fixed
     * array then re assign it and notify the adapter
     */
    fun setTags(items: List<String>) {
        val tempItems = items.toMutableList()
        tempItems.addAll(items)
        this.items = emptyList()
        this.items = tempItems.toList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val restaurantView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_items, parent, false)
        return ViewHolder(restaurantView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        holder.name.text = item

    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * View holder for our items
     */
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        var name: TextView = itemView.findViewById<TextView>(R.id.tv_item_name)

    }
}