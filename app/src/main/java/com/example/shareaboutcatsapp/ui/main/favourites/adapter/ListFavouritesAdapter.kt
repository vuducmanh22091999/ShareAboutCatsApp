package com.example.shareaboutcatsapp.ui.main.favourites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModelItem
import kotlinx.android.synthetic.main.item_favourites_1.view.*

class ListFavouritesAdapter(
    private var listFavouritesModelItem: List<FavouritesModelItem>,
    val onClick: (Int, Int) -> Unit,
    val onClickDelete: (Int, Int) -> Unit
) : RecyclerView.Adapter<ListFavouritesAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindDataViewHolder(favouritesModelItem: FavouritesModelItem) {
            Glide.with(itemView.context).load(favouritesModelItem.image.url).placeholder(R.drawable.img_placeholder)
                .into(itemView.itemImageFavourites)
            itemView.itemSubIDFavourites.text = favouritesModelItem.sub_id
            itemView.itemImageDeleteMyFavourites.setImageResource(R.drawable.ic_delete)

            itemView.setOnClickListener {
                onClick(adapterPosition, favouritesModelItem.id)
            }

            itemView.itemImageDeleteMyFavourites.setOnClickListener {
                onClickDelete(adapterPosition, favouritesModelItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_favourites_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataViewHolder(listFavouritesModelItem[position])
    }

    override fun getItemCount(): Int = listFavouritesModelItem.size

    fun filterFavourites(filteredFavourites: List<FavouritesModelItem>) {
        listFavouritesModelItem = filteredFavourites
        notifyDataSetChanged()
    }
}