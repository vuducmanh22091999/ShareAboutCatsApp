package com.example.shareaboutcatsapp.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModelItem
import com.example.shareaboutcatsapp.data.model.image.ImageModelItem
import kotlinx.android.synthetic.main.item_categories.view.*
import kotlinx.android.synthetic.main.item_favourites.view.*

class ListTest(
    private val listImageModelItem: List<ImageModelItem>,
    val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ListTest.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindDataViewHolder(imageModelItem: ImageModelItem) {
            itemView.itemSubIDFavourites.text = imageModelItem.categoriesModelItem.forEach {
                it.name
            }.toString()

            Glide.with(itemView.context).load(imageModelItem.url).into(itemView.itemImageFavourites)
            itemView.setOnClickListener {
                onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_favourites, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataViewHolder(listImageModelItem[position])
    }

    override fun getItemCount(): Int = listImageModelItem.size

}