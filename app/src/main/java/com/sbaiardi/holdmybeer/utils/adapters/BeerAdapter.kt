package com.sbaiardi.holdmybeer.utils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sbaiardi.holdmybeer.R
import com.sbaiardi.holdmybeer.model.Beer

class BeerAdapter(private val onClick: (Beer) -> Unit): ListAdapter<Beer, BeerAdapter.ViewHolder>(BeerDiffCallback) {
    class ViewHolder(view: View, val onClick: (Beer) -> Unit): RecyclerView.ViewHolder(view) {
        private val textViewName: TextView = view.findViewById(R.id.txt_name)
        private val textViewDescription: TextView = view.findViewById(R.id.text_description)
        private val imageViewItem: ImageView = view.findViewById(R.id.img_item)
        lateinit var beer: Beer
        init {
            view.setOnClickListener {
                onClick(beer)
            }
        }
        fun bind(beer: Beer){
            this.beer = beer
            textViewName.text = beer.name
            textViewDescription.text = beer.tagline
            Glide.with(imageViewItem.context).load(beer.image_url).into(imageViewItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_beers_recyclerview_layout,parent,false)
        return ViewHolder(view,onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val municipality = getItem(position)
        holder.bind(municipality)
    }


}
object BeerDiffCallback : DiffUtil.ItemCallback<Beer>() {
    override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem.id == newItem.id
    }
}