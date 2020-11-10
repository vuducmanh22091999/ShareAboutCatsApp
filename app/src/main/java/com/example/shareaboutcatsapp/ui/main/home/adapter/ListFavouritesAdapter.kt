package com.example.shareaboutcatsapp.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModelItem
import kotlinx.android.synthetic.main.item_favourites.view.*

class ListFavouritesAdapter(private val listFavourites: List<FavouritesModelItem>) : RecyclerView.Adapter<ListFavouritesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindDataViewHolder(favouritesModelItem: FavouritesModelItem) {
            Glide.with(itemView.context).load(favouritesModelItem.image.url).into(itemView.itemImageFavourites)
            itemView.itemSubIDFavourites.text = favouritesModelItem.sub_id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_favourites, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataViewHolder(listFavourites[position])
    }

    override fun getItemCount(): Int = listFavourites.size
}